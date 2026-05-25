# PRD: Epic 2 - Rental Data Engine

Date: 2026-05-25

## Source References

- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/product/2026-05-15-common-rental-owner-app-prd.md`
- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/superpowers/specs/2026-05-15-common-rental-owner-app-design.md`

## Epic Summary

Build the offline-first data foundation that powers RentalIn's inventory, rental, customer, payment, availability, and dashboard workflows.

This epic turns the static UI shell from Epic 1 into a real owner operations system. The app must store local records, expose reactive data streams, enforce rental business rules outside the UI, and provide tested domain behavior for later feature screens.

## Product Goal

Support all visible MVP states with real local data so owners can safely manage rentals without internet, accounts, cloud sync, or paid infrastructure.

## Target User

Primary user: a single rental business owner or operator.

The first target business is hiking gear rental, but the data model must stay flexible enough for other simple rental businesses such as camera gear, tools, event equipment, costumes, and sports equipment.

## Problem

Rental owners currently rely on notebooks, spreadsheets, and chat messages. Those tools do not reliably prevent double-booking, track active rentals, calculate balances, or preserve operational history. Before RentalIn can ship feature screens, it needs a trustworthy local data engine with business rules that are not duplicated in Compose UI.

## Scope

### In Scope

- Shared domain models for rental operations.
- Room-backed local persistence.
- Repositories for rentals, inventory, customers, and settings-adjacent data needed by this epic.
- Offline-first local storage as source of truth.
- Availability checks for serialized units and quantity stock.
- Date overlap logic.
- Rental lifecycle status rules.
- Derived overdue and due-today display states.
- Payment balance and payment status calculation.
- Dashboard summary use cases.
- Test fixtures and deterministic local tests.

### Out of Scope

- Cloud sync.
- Login or account ownership.
- Online payment processing.
- Customer-facing marketplace data.
- Android scheduled notifications.
- Full backup and restore.
- UI implementation beyond what is needed to validate data contracts.

## Core Concepts

### Item

Represents a rentable item type such as tent, sleeping bag, camera body, drill, or speaker.

Required data:

- Name.
- Category.
- Rental unit type: serialized or quantity.
- Default price.
- Notes.
- Active or archived state.

### Serialized Unit

Represents one physical unit under a serialized item.

Required data:

- Parent item.
- Unit code or label.
- Condition.
- Status.
- Notes.

Supported statuses:

- Available.
- Reserved.
- Rented.
- Maintenance.
- Lost.
- Archived.

### Quantity Stock

Represents interchangeable stock under one item record.

Required data:

- Parent item.
- Total quantity.
- Optional low-stock threshold.
- Notes.

Availability is calculated from total stock minus overlapping reserved and active quantities.

### Customer

Represents an optional reusable customer record.

Required data:

- Name.
- Phone.
- Optional ID.
- Optional address.
- Notes.
- Rental history relationship.

Rentals must also support typed customer details without requiring a reusable customer record.

### Rental

Represents a booking and operational record.

Required data:

- Customer details and optional saved customer link.
- Start date.
- Expected return date.
- Actual return date.
- Rental status.
- Payment inputs.
- Rental lines.
- Condition and notes fields for pickup and return.

Stored statuses:

- Reserved.
- Active.
- Returned.
- Cancelled.

Derived display states:

- Overdue.
- Due today.

### Rental Line

Connects a rental to either one serialized unit or a quantity item.

Required data:

- Rental reference.
- Item reference.
- Unit reference for serialized items.
- Quantity for quantity items.
- Price.
- Condition notes.

## Functional Requirements

### FR1: Local Database

The app must persist item, unit, quantity stock, customer, rental, rental line, payment, and export-relevant records locally.

Acceptance criteria:

- App data remains available after app restart.
- Core feature modules can read data through repositories.
- Feature modules do not query Room DAOs directly.

### FR2: Repository APIs

Repositories must expose reactive read streams and suspend write operations.

Acceptance criteria:

- Rentals, inventory, and customers can be observed as `Flow` streams.
- Create, update, archive, lifecycle transition, and payment write APIs are suspend functions.
- Public repository APIs expose domain models, not database entities.

### FR3: Date Validation

Expected return date cannot be before start date.

Acceptance criteria:

- Invalid date ranges are rejected before persistence.
- Validation result is explicit enough for UI to show an owner-friendly error.

### FR4: Serialized Availability

The app must determine whether a serialized unit can be rented for a requested date range.

Acceptance criteria:

- Overlapping reserved and active rentals block the same unit.
- Returned and cancelled rentals do not block availability.
- Maintenance, lost, and archived units are unavailable regardless of date.
- Existing rental edits can exclude the current rental from conflict checks.

### FR5: Quantity Availability

The app must calculate available stock for quantity items.

Acceptance criteria:

- Available quantity equals total quantity minus overlapping reserved and active quantities.
- Returned and cancelled rentals do not reduce available quantity.
- Requested quantity is valid only when remaining quantity is greater than or equal to requested quantity.
- Low or fully booked stock can be surfaced to dashboard consumers.

### FR6: Rental Lifecycle

The app must support reserved, active, returned, and cancelled rental states.

Acceptance criteria:

- Reserved rental can become active.
- Active rental can become returned.
- Reserved or active rental can be cancelled when product rules allow it.
- Returned rentals capture actual return date and return details.
- Returned serialized units become available unless marked maintenance, lost, or archived.

### FR7: Derived Operational States

The app must derive overdue and due-today states without storing them as rental statuses.

Acceptance criteria:

- A rental is overdue when status is active and expected return date is before current date.
- A rental is due today when status is active and expected return date equals current date.
- Reserved rentals are not overdue.
- The current date is injectable for deterministic tests.

### FR8: Payment Calculation

The app must calculate balance and payment status from rental fee, deposit, amount paid, refund state, and return adjustments.

Acceptance criteria:

- Negative price, deposit, payment, refund, and quantity values are invalid.
- Payment states include unpaid, partial, paid, refund due, and refunded.
- Balance due is deterministic and testable.
- Payment logic avoids behaving like full accounting software.

### FR9: Dashboard Summary Use Cases

The data engine must provide use cases for dashboard counts and attention lists.

Acceptance criteria:

- Consumers can retrieve active, overdue, due-today, unpaid, low-stock, fully booked, maintenance, and lost summaries.
- Dashboard use cases depend on repositories/domain APIs, not database DAOs.

## Non-Functional Requirements

- Must work offline.
- Local storage is the source of truth.
- Business rules must be testable without Android UI.
- Domain models should not depend on Android UI libraries.
- Feature modules must not depend directly on each other.
- Database entities must not leak into feature UI state.

## Dependencies

- Epic 1: App Foundation & Design System for module structure and build conventions.

## Success Metrics

- Owner can avoid double-booking during normal rental creation.
- UI can be driven by real local data.
- Availability and balance logic are covered by deterministic tests.
- Later feature epics can consume repositories without duplicating business rules.

## Risks

- Supporting both serialized and quantity stock can complicate availability logic.
- Payment and refund logic can become too broad if it attempts to become accounting software.
- Poor repository boundaries could cause feature modules to depend on database details.

## Release Checklist

- Domain models created.
- Room entities and DAOs created.
- Repository contracts and implementations created.
- Availability use cases tested.
- Payment calculation tested.
- Rental lifecycle transitions tested.
- Dashboard summary use cases tested.
- Test fixtures added to `core:testing`.
