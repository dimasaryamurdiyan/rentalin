# PRD: Epic 4 - Rentals List & Status Tracking

Date: 2026-05-25

## Source References

- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/product/2026-05-15-common-rental-owner-app-prd.md`
- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/superpowers/specs/2026-05-15-common-rental-owner-app-design.md`

## Epic Summary

Build the Rentals list experience so owners can quickly find rentals by operational state, payment state, customer, item, and date.

## Product Goal

Give owners a compact working list of current and historical rentals, with overdue, due-today, active, reserved, returned, cancelled, and unpaid states visible without spreadsheet-style manual filtering.

## Target User

Primary user: a single rental business owner checking what is currently out, what is coming up, what was returned, and what still needs payment.

## Problem

Rental records become hard to manage when they live across notebooks, chat threads, and payment notes. Owners need one place to see rental status and payment status together. The list must be fast enough for daily use and clear enough to prevent missing overdue or unpaid rentals.

## Scope

### In Scope

- Rentals list.
- Filter chips for operational and payment states.
- Search rentals.
- Sort rentals.
- Rental row summary.
- Rental detail navigation entry point.
- Empty states for each filter.
- Visual distinction for overdue and due-today rentals.

### Out of Scope

- Full create rental flow.
- Return workflow details.
- Payment update workflow details.
- CSV export.
- Push notifications.
- Calendar integration.

## User Stories

- As a rental owner, I want to view active rentals so I know what items are currently out.
- As a rental owner, I want to filter overdue rentals so I know who needs follow-up.
- As a rental owner, I want payment state visible in the list so I know what is unpaid without opening every rental.
- As a rental owner, I want to search by customer or item so I can answer questions quickly.

## Functional Requirements

### FR1: Rentals List

The Rentals screen must show a compact list of rental records.

Each row must include:

- Customer initials or simple avatar.
- Customer name.
- Item summary.
- Date range.
- Rental amount or balance summary.
- Payment label.
- Rental status chip.

Acceptance criteria:

- Active and reserved rentals are easy to scan.
- Returned and cancelled rentals remain accessible.
- Payment status appears in the row, not only in detail.

### FR2: Status Filters

The feature must support filtering rentals by state.

Required filters:

- All.
- Active.
- Reserved.
- Overdue.
- Returned.
- Cancelled.
- Unpaid.

Acceptance criteria:

- Selecting a filter updates the list.
- Overdue is derived from active rentals past expected return date.
- Unpaid includes unpaid and partial payment states.

### FR3: Search

The owner must be able to search rentals.

Acceptance criteria:

- Search matches customer name.
- Search matches customer phone where available.
- Search matches item names and serialized unit labels where available.
- No-results state clearly explains that no matching rentals were found.

### FR4: Sort

The owner must be able to sort the list for daily work.

Minimum sort options:

- Expected return date.
- Start date.
- Recently updated.
- Customer name.

Acceptance criteria:

- Default sort prioritizes operational attention: overdue, due today, active, then upcoming reserved.
- Historical returned or cancelled rentals do not bury active issues.

### FR5: Overdue and Due-Today Display

The list must visually call out urgent rental states.

Acceptance criteria:

- Overdue rentals use the overdue warning color.
- Due-today rentals are visually distinct from normal active rentals.
- Warning colors are reserved for operational attention.

### FR6: Detail Navigation

The owner must be able to open a rental detail from any row.

Acceptance criteria:

- Row tap navigates to the rental detail destination.
- Navigation does not require feature-to-feature dependencies.
- Missing or deleted related data is handled gracefully in display text.

## UX Requirements

- Use dense, readable rows with clear hierarchy.
- Avoid large promotional cards or marketplace patterns.
- Use status chips consistently with `core:ui` and `core:designsystem`.
- Keep primary actions easy to reach from the Rentals screen.
- Add `@Preview` for every composable view.

## Data Requirements

The feature consumes:

- Rental summaries.
- Customer display data.
- Rental line item summaries.
- Derived rental state.
- Payment state.

The feature writes:

- Filter and sort UI state only.
- No rental lifecycle mutation is required for this epic except navigation handoff.

## Dependencies

- Epic 1: App Foundation & Design System.
- Epic 2: Rental Data Engine.
- Epic 3: Inventory Catalog for meaningful item data.

## Success Metrics

- Owner can quickly find active, overdue, returned, cancelled, and unpaid rentals.
- Overdue and due-today rentals are visible without opening detail.
- Payment state is visible from the list.

## Risks

- Too many filters can slow down daily use.
- Row content can become cluttered if every field is shown.
- Derived states can be inconsistent if duplicated in the UI instead of using domain rules.

## Release Checklist

- Rentals list implemented.
- Status and payment filter chips implemented.
- Search implemented.
- Sort implemented.
- Empty states implemented.
- Detail navigation entry point wired.
- ViewModel tests cover filter and sort behavior.
- Compose previews added.
