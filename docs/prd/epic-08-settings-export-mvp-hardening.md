# PRD: Epic 8 - Settings, Export & MVP Hardening

Date: 2026-05-25

## Source References

- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/product/2026-05-15-common-rental-owner-app-prd.md`
- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/superpowers/specs/2026-05-15-common-rental-owner-app-design.md`

## Epic Summary

Finish MVP support features that make RentalIn usable for first real operations: settings, CSV export, destructive action confirmations, validation polish, empty/loading/error states, manual QA, and release readiness.

## Product Goal

Ship a stable first private release that an owner can use without cloud setup, paid infrastructure, login, or technical help.

## Target User

Primary user: a single rental business owner who needs confidence that records can be reviewed, exported, and managed safely.

## Problem

Core workflows are not enough for a usable MVP. Owners need exports for manual records, clear settings, safe destructive actions, and reliable states when data is empty, loading, invalid, or failing. Without this hardening, the app may work in demos but fail during real daily use.

## Scope

### In Scope

- Settings screen.
- CSV export entry points.
- CSV reports for rentals, payments, customers, and inventory.
- Export success and failure states.
- Retry path for export failures.
- Destructive action confirmations.
- Form validation polish.
- Empty, loading, and error states.
- Manual QA checklist.
- Unit test cleanup.
- Release build readiness.

### Out of Scope

- Login.
- Cloud sync.
- Full backup and restore.
- Online payments.
- Push notifications.
- Team roles.
- Workflow builder.
- Advanced analytics.

## User Stories

- As a rental owner, I want to export CSV reports so I can review records in a spreadsheet or keep manual backups outside the app.
- As a rental owner, I want delete and archive actions to confirm before they happen so I do not lose data by accident.
- As a rental owner, I want clear failure messages so I know what to do next.
- As a rental owner, I want the app to feel stable enough for first real business use.

## Functional Requirements

### FR1: Settings Screen

The app must provide a compact settings destination outside the primary bottom navigation.

Settings content:

- Business display name if available.
- Export entry points.
- App information.
- MVP-safe preferences that already exist in data.

Acceptance criteria:

- Settings is reachable from compact menu or app shell.
- Settings is not a primary bottom navigation tab.
- No account, sync, payment, or team settings are introduced for MVP.

### FR2: CSV Export

The owner must be able to export readable CSV reports.

Required reports:

- Rentals.
- Payments.
- Customers.
- Inventory.

Acceptance criteria:

- CSV files have stable column headers.
- Export content is readable in common spreadsheet tools.
- Export does not claim to be full app backup or restore.
- Empty datasets export either a header-only file or show a clear empty state.

### FR3: Export States

The export flow must show status and recovery paths.

Acceptance criteria:

- Export success is visible.
- Export failure explains what happened in owner-friendly language.
- Failed export offers retry where possible.
- File destination behavior follows Android platform expectations.

### FR4: Destructive Action Confirmations

The app must protect actions that remove or hide operational data.

Actions requiring confirmation:

- Archive item.
- Archive serialized unit.
- Delete or cancel records where implemented.
- Any action that can make records disappear from normal workflows.

Acceptance criteria:

- Confirmation copy explains the consequence.
- Historical rental records remain readable after archive.
- Confirmations are not used for harmless navigation or filter changes.

### FR5: Validation Polish

All MVP forms must use consistent validation behavior.

Acceptance criteria:

- Required fields show clear messages.
- Expected return date before start date is blocked.
- Negative price, deposit, payment, refund, and quantity values are blocked.
- Unavailable inventory selection is blocked.
- Validation messages use domain language owners understand.

### FR6: Empty, Loading, and Error States

All primary MVP screens must handle non-happy paths.

Required screens:

- Dashboard.
- Rentals.
- Inventory.
- Customers.
- Settings/export.
- Create rental.
- Customer detail.

Acceptance criteria:

- Empty states explain the next useful action.
- Loading states do not cause layout jumps.
- Error states provide retry or navigation recovery where possible.

### FR7: MVP QA

The epic must produce a manual QA pass for the first release.

Core flows:

- Add inventory.
- Create rental.
- See active rental.
- See due-today or overdue rental.
- Mark pickup/start.
- Mark returned.
- Record payment status changes where implemented.
- Mark unit maintenance or lost.
- View customer history.
- Export CSV.

Acceptance criteria:

- No blocker bugs in dashboard, rentals, inventory, customer detail, or create rental.
- Known limitations are documented.
- Release candidate can be built.

## UX Requirements

- Keep settings compact.
- Keep export language clear: reporting and manual records, not full backup.
- Use clear destructive confirmation titles and consequences.
- Use consistent validation and error surfaces across features.
- Avoid adding account, sync, or paid-service language.

## Data Requirements

The feature consumes:

- Rental records.
- Payment fields and derived payment states.
- Customer records.
- Inventory records.
- Business/app settings.

The feature writes:

- Export files through Android file APIs.
- Settings changes only where MVP preferences exist.
- No cloud or account records.

## Dependencies

- Epic 1: App Foundation & Design System.
- Epic 2: Rental Data Engine.
- Epic 3: Inventory Catalog.
- Epic 4: Rentals List & Status Tracking.
- Epic 5: Create Rental Flow.
- Epic 6: Customer Detail & History.
- Epic 7: Dashboard & Attention Queue.

## Success Metrics

- Owner can export records without technical help.
- Owner can complete the core rental loop without blocker bugs.
- Core screens handle empty, loading, and error states.
- MVP remains private, offline-first, and free to operate.

## Risks

- CSV export may be mistaken for backup and restore if wording is unclear.
- Hardening can expand indefinitely without a strict release boundary.
- File destination behavior can vary across Android versions.

## Release Checklist

- Settings screen implemented.
- Rentals CSV export implemented.
- Payments CSV export implemented.
- Customers CSV export implemented.
- Inventory CSV export implemented.
- Export success and failure states implemented.
- Destructive confirmations implemented.
- Validation pass completed.
- Empty/loading/error states completed.
- Manual QA checklist completed.
- Release build created.
