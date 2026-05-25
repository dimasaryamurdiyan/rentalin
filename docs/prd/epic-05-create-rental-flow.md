# PRD: Epic 5 - Create Rental Flow

Date: 2026-05-25

## Source References

- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/product/2026-05-15-common-rental-owner-app-prd.md`
- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/superpowers/specs/2026-05-15-common-rental-owner-app-design.md`

## Epic Summary

Build the create rental workflow so an owner can record a rental with customer, dates, items, deposit, amount paid, condition notes, availability warnings, and balance calculation.

## Product Goal

Let an owner create a complete rental in under one minute after inventory exists, while preventing accidental double-bookings and invalid payment or date records.

## Target User

Primary user: a rental owner serving a customer at pickup, by phone, or from a message. The owner may need to move quickly and may not have a saved customer record yet.

## Problem

Creating a rental is the most important operational workflow. If it is too slow, owners will return to notebooks. If it is too permissive, they can double-book items, enter bad dates, or lose payment context. The flow must balance fast entry with strong validation.

## Scope

### In Scope

- Select existing customer.
- Type customer details without saving reusable customer.
- Option to save typed details as reusable customer.
- Start date and expected return date.
- Add serialized units and quantity stock.
- Remove selected items.
- Show selected item price.
- Deposit field.
- Amount paid field.
- Notes field.
- Condition before handoff.
- Availability warning callout.
- Total, paid, deposit, and balance summary.
- Create rental action.
- Validation before save.

### Out of Scope

- Full customer detail management.
- Return workflow.
- Late fees and post-return adjustments.
- Online payment capture.
- Signature capture.
- Photo attachments.
- Notifications.

## User Stories

- As a rental owner, I want to create a rental with customer, dates, items, price, deposit, and notes so I have one complete operational record.
- As a rental owner, I want the app to warn me when an item or quantity is unavailable for selected dates so I avoid double-booking.
- As a rental owner, I want to type customer details quickly during rental creation so I can serve walk-in customers fast.
- As a rental owner, I want to see balance due before saving so payment expectations are clear.

## Functional Requirements

### FR1: Customer Section

The flow must support both saved customer selection and typed customer details.

Acceptance criteria:

- Customer name is required.
- Customer contact warning appears when phone or contact detail is missing.
- Owner can select an existing saved customer.
- Owner can type a customer without saving.
- Owner can choose to save typed customer details when appropriate.

### FR2: Schedule Section

The flow must capture rental start and expected return dates.

Acceptance criteria:

- Start date is required.
- Expected return date is required.
- Expected return date cannot be before start date.
- Invalid date errors appear before saving.

### FR3: Item Selection

The flow must allow selecting serialized units and quantity stock.

Acceptance criteria:

- Archived, maintenance, and lost units cannot be selected.
- Quantity item selection requires quantity greater than zero.
- Requested quantity cannot exceed availability for the date range.
- Selected items can be removed before saving.
- Item price is visible and editable if MVP pricing rules allow manual override.

### FR4: Availability Warnings

The flow must warn before saving conflicts.

Acceptance criteria:

- Serialized unit conflicts are detected for overlapping reserved or active rentals.
- Quantity stock conflicts are detected when requested quantity exceeds remaining stock.
- Returned and cancelled rentals do not block availability.
- Warning text clearly identifies the problematic item or quantity.
- The owner cannot accidentally save a conflicting reserved or active rental.

### FR5: Payment and Deposit Section

The flow must capture payment inputs.

Required fields:

- Rental fee.
- Deposit amount.
- Amount paid.

Acceptance criteria:

- Money values cannot be negative.
- Balance due is calculated before saving.
- Payment state is previewed when possible.
- Deposit and amount paid are labeled clearly to avoid accounting confusion.

### FR6: Condition and Notes

The flow must support operational notes.

Acceptance criteria:

- Owner can enter condition before handoff.
- Owner can enter pickup notes or general notes.
- Notes are optional.
- Notes are persisted with the rental.

### FR7: Create Rental Action

The owner must be able to save a valid rental.

Acceptance criteria:

- Save is blocked when required fields are invalid.
- Success returns the owner to a useful next destination, such as rental detail or rentals list.
- Failure shows an owner-friendly error and preserves entered form state.

## UX Requirements

- Form sections: customer, schedule, items, payment/deposit, condition, notes.
- Keep form compact and practical.
- Use a sticky or clearly visible create action.
- Show availability warnings near selected items.
- Use warning colors only for conflict, due, unpaid, maintenance, and lost states.
- Add `@Preview` for every composable view.

## Data Requirements

The feature consumes:

- Saved customers.
- Active inventory.
- Serialized unit availability.
- Quantity stock availability.
- Default item pricing.

The feature writes:

- Rental record.
- Rental lines.
- Optional reusable customer record.
- Initial payment/deposit values.
- Condition and notes fields.

## Dependencies

- Epic 1: App Foundation & Design System.
- Epic 2: Rental Data Engine.
- Epic 3: Inventory Catalog.
- Epic 4: Rentals List & Status Tracking for destination handoff.

## Success Metrics

- Owner can create a rental in under one minute after inventory exists.
- Owner can avoid double-booking during normal rental creation.
- Balance due is visible before saving.
- Invalid dates and negative money values are blocked.

## Risks

- Combining customer, inventory, schedule, and payment inputs can make the form feel long.
- Availability checks can feel confusing if warnings are vague.
- Over-flexible pricing can turn MVP into accounting software.

## Release Checklist

- Create rental ViewModel implemented.
- Customer section implemented.
- Schedule section implemented.
- Item selection implemented.
- Availability warning callout implemented.
- Payment and balance summary implemented.
- Validation tests added.
- Conflict tests added.
- Compose previews added.
