# PRD: Epic 6 - Customer Detail & History

Date: 2026-05-25

## Source References

- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/product/2026-05-15-common-rental-owner-app-prd.md`
- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/superpowers/specs/2026-05-15-common-rental-owner-app-design.md`

## Epic Summary

Build customer detail and rental history so owners can understand a customer relationship, contact the customer, review balances, and start a new rental from the customer profile.

## Product Goal

Give the owner a lightweight customer record that helps repeat rentals without turning RentalIn into a CRM.

## Target User

Primary user: a rental owner who wants to find repeat customer information, review rental history, and understand unpaid balance or deposit context quickly.

## Problem

Repeat customers often appear across chat messages, handwritten notes, and old rental records. Owners need a simple way to reuse customer contact details and inspect history without requiring every rental to be attached to a saved customer.

## Scope

### In Scope

- Customer profile header.
- Customer status chip.
- Contact actions: call, message, email, edit.
- Account overview.
- Notes section.
- Tabs for rentals, payments, and activity.
- Rental history rows with status chips.
- New rental action from customer detail.
- Edit saved customer details.

### Out of Scope

- Full CRM features.
- Marketing campaigns.
- Loyalty programs.
- Customer login.
- Customer-facing browsing.
- Cloud contact sync.
- Team notes or staff assignment.

## User Stories

- As a rental owner, I want to save repeat customers so I can reuse contact details and view rental history.
- As a rental owner, I want to see a customer's unpaid balance so I know whether payment follow-up is needed.
- As a rental owner, I want to start a new rental from a customer detail screen so repeat checkout is faster.
- As a rental owner, I want typed rental customer details to remain valid even if they are not saved as reusable customers.

## Functional Requirements

### FR1: Customer Profile Header

The detail screen must summarize the saved customer.

Displayed fields:

- Name.
- Phone.
- Optional email if present.
- Optional ID.
- Optional address.
- Customer status chip.

Acceptance criteria:

- Missing optional data does not create broken layout.
- Customer status reflects operational context such as active rental, unpaid balance, or clear account.

### FR2: Contact Actions

The detail screen must expose practical contact actions.

Actions:

- Call.
- Message.
- Email.
- Edit.

Acceptance criteria:

- Actions appear only when supporting data exists.
- Actions use platform intents where applicable.
- The app handles missing phone or email gracefully.

### FR3: Account Overview

The screen must summarize the customer relationship.

Metrics:

- Total rentals.
- Total spent.
- Unpaid balance.
- Security deposit held or deposit context.

Acceptance criteria:

- Totals are derived from local rental records.
- Returned and cancelled rentals are represented consistently.
- Unpaid balance is easy to spot.

### FR4: Notes

The owner must be able to view and edit customer notes.

Acceptance criteria:

- Notes are optional.
- Notes persist locally.
- Notes are not required for rental creation.

### FR5: Rental History

The customer detail must show rental history.

Acceptance criteria:

- Rental history rows include item summary, date range, status, and payment label.
- Rows use the same operational status language as the Rentals list.
- Tapping a history row opens rental detail.
- Typed-only rental customers are not forced into this screen unless saved later.

### FR6: Payments Tab

The detail must show payment-related history at a lightweight level.

Acceptance criteria:

- Payment tab summarizes rental fee, amount paid, balance, and refund state by rental.
- It does not attempt full accounting ledger behavior.
- Unpaid and refund-due states are visible.

### FR7: Activity Tab

The detail must show important customer activity.

Acceptance criteria:

- Activity can include rental created, pickup, return, payment recorded, and status changes when available.
- If no activity model exists yet, this tab may initially use rental history events generated from rental records.

### FR8: New Rental from Customer

The owner must be able to start a new rental from customer detail.

Acceptance criteria:

- New rental action opens create rental with customer prefilled.
- Customer can still be edited in the rental flow before saving.

## UX Requirements

- Keep the screen practical and list-focused.
- Avoid CRM-style dashboards or marketing segmentation.
- Use compact account summary cards or rows.
- Use status chips consistently with other features.
- Add `@Preview` for every composable view.

## Data Requirements

The feature consumes:

- Customer record.
- Rental summaries for that customer.
- Payment summaries for that customer.
- Derived account overview.

The feature writes:

- Customer edits.
- Customer notes.
- Navigation request to create rental with prefilled customer.

## Dependencies

- Epic 1: App Foundation & Design System.
- Epic 2: Rental Data Engine.
- Epic 4: Rentals List & Status Tracking.
- Epic 5: Create Rental Flow for new-rental handoff.

## Success Metrics

- Owner can understand a customer relationship at a glance.
- Owner can start a new rental from the customer profile.
- Customer history reflects real rental records.
- Typed customer rental records remain supported without mandatory saved customer conversion.

## Risks

- The feature can drift into CRM scope if too many customer management concepts are added.
- Payment history can become confusing if it mimics accounting software.
- Contact actions must avoid failing awkwardly when optional fields are missing.

## Release Checklist

- Customer detail screen implemented.
- Account overview implemented.
- Contact actions implemented.
- Notes edit implemented.
- Rentals tab implemented.
- Payments tab implemented.
- Activity tab implemented or intentionally backed by rental events.
- New rental handoff implemented.
- ViewModel tests added.
- Compose previews added.
