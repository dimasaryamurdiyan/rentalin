# PRD: Epic 7 - Dashboard & Attention Queue

Date: 2026-05-25

## Source References

- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/product/2026-05-15-common-rental-owner-app-prd.md`
- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/superpowers/specs/2026-05-15-common-rental-owner-app-design.md`

## Epic Summary

Build the Dashboard as the daily operating home for RentalIn. It must show what needs attention now: active rentals, due-today returns, overdue rentals, unpaid rentals, low or fully booked stock, and maintenance or lost units.

## Product Goal

When the owner opens the app, they should immediately know what needs action today without opening filters or running reports.

## Target User

Primary user: a rental owner beginning or checking the day, often between customer messages, pickups, returns, and inventory work.

## Problem

Owners can miss overdue rentals, unpaid balances, due-today returns, and unavailable stock when records are scattered. RentalIn needs an opening screen that highlights operational attention, not a marketing home page or generic analytics dashboard.

## Scope

### In Scope

- Dashboard greeting or header.
- Summary cards.
- Needs attention list.
- Attention row types for overdue, due today, payment due, maintenance, lost, low-stock, and fully booked stock.
- View all actions.
- Floating or prominent create rental action.
- Deep links to relevant rental, customer, and inventory destinations.
- Empty state when no attention is needed.

### Out of Scope

- Business analytics charts.
- Revenue forecasting.
- Push notifications.
- Calendar sync.
- Multi-staff assignments.
- Customer marketplace metrics.

## User Stories

- As a rental owner, I want to see active rentals when I open the app so I know what is currently out.
- As a rental owner, I want overdue rentals highlighted so I can follow up.
- As a rental owner, I want due-today returns visible so I can prepare for returns.
- As a rental owner, I want unpaid rentals visible so I know what needs payment follow-up.
- As a rental owner, I want maintenance and lost units visible so I do not rent unavailable gear.

## Functional Requirements

### FR1: Dashboard Summary Cards

The dashboard must show compact operational summary cards.

Required cards:

- Active.
- Due today.
- Overdue.
- Unpaid.
- Maintenance.

Acceptance criteria:

- Cards show counts from real local data.
- Overdue and unpaid cards use warning treatment.
- Tapping a card navigates to the appropriate filtered list or destination.

### FR2: Needs Attention List

The dashboard must show the most important actionable rows.

Attention row types:

- Overdue rental.
- Due-today rental.
- Payment due.
- Maintenance unit.
- Lost unit.
- Low-stock quantity item.
- Fully booked quantity item.

Acceptance criteria:

- Rows include a concise title, supporting detail, status chip, and destination.
- Highest urgency appears first: overdue, due today, unpaid, maintenance/lost, stock warnings.
- Empty state appears when no records need attention.

### FR3: Deep Links

Dashboard rows and cards must route to relevant screens.

Acceptance criteria:

- Rental rows open rental detail.
- Customer-related rows can open customer detail where appropriate.
- Inventory rows open inventory detail.
- View all actions open filtered lists.
- Navigation contracts avoid direct feature-to-feature dependencies.

### FR4: Create Rental Action

The dashboard must make rental creation easy to start.

Acceptance criteria:

- Primary create rental action is visible on the dashboard.
- Action opens the create rental flow.
- Action does not obscure important attention rows.

### FR5: Derived State Accuracy

Dashboard values must use shared domain/data rules.

Acceptance criteria:

- Overdue means active rental with expected return date before current date.
- Due today means active rental with expected return date equal to current date.
- Unpaid includes unpaid and partial payment states.
- Maintenance and lost counts reflect current inventory unit states.
- Low or fully booked stock reflects quantity availability rules.

## UX Requirements

- Build an actual tool screen, not a landing page.
- Keep the dashboard dense and readable.
- Use warning colors only for operational attention.
- Avoid decorative hero layouts and oversized promotional cards.
- Keep Settings and CSV export behind a compact menu or settings destination, not as a dashboard centerpiece.
- Add `@Preview` for every composable view.

## Data Requirements

The feature consumes:

- Dashboard summary use cases.
- Attention queue use case.
- Rental summaries.
- Inventory status summaries.
- Payment summaries.

The feature writes:

- No core records directly.
- Navigation actions to create rental and detail screens.

## Dependencies

- Epic 1: App Foundation & Design System.
- Epic 2: Rental Data Engine.
- Epic 3: Inventory Catalog.
- Epic 4: Rentals List & Status Tracking.
- Epic 5: Create Rental Flow.
- Epic 6: Customer Detail & History for customer-related deep links.

## Success Metrics

- Owner can identify overdue rentals from the dashboard without opening filters.
- Owner can see due-today and unpaid work immediately.
- Dashboard supports the daily operating loop.
- Dashboard attention rows deep link to useful next actions.

## Risks

- Dashboard can become too broad if analytics and reporting are added early.
- Too many warning rows can make the screen noisy.
- Derived state duplication can cause inconsistencies with Rentals and Inventory.

## Release Checklist

- Summary cards implemented.
- Needs attention list implemented.
- Empty state implemented.
- Create rental action implemented.
- Deep links wired.
- ViewModel tests cover summary and attention ordering.
- Compose previews added.
