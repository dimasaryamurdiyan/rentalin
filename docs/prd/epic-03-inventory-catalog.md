# PRD: Epic 3 - Inventory Catalog

Date: 2026-05-25

## Source References

- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/product/2026-05-15-common-rental-owner-app-prd.md`
- `/Users/singaludra/Documents/Codex/2026-05-15/i-want-to-make-common-rental/docs/superpowers/specs/2026-05-15-common-rental-owner-app-design.md`

## Epic Summary

Build the Inventory feature so owners can create, organize, edit, archive, and monitor rentable items. The feature must clearly support both serialized physical units and interchangeable quantity stock.

## Product Goal

Let a rental owner maintain a practical local catalog that prevents unavailable, maintenance, lost, or archived inventory from being selected for new reserved or active rentals.

## Target User

Primary user: a single rental business owner who needs a quick, reliable view of what can be rented today and what needs maintenance.

Example hiking gear inventory:

- Tents.
- Trekking poles.
- Camping stoves.
- Sleeping bags.
- Backpacks.
- Headlamps.

The feature must also work for camera, event, tool, costume, and sports equipment rental businesses.

## Problem

Small rental businesses often mix individually tracked items with interchangeable stock. A tent may need a unit code and condition notes, while headlamps may only need total quantity. If the app treats every item the same, the owner either loses physical-unit traceability or wastes time manually creating records for interchangeable stock.

## Scope

### In Scope

- Inventory list.
- Category filter chips.
- Search inventory.
- Filter inventory by status and item type.
- Add and edit item records.
- Add and edit serialized units.
- Add and edit quantity stock.
- Archive item or unit.
- Mark serialized units as available, maintenance, lost, or archived.
- Show available, rented, maintenance, lost, low-stock, and fully booked states.
- Display unit codes for serialized items.
- Display quantity counts for stock items.

### Out of Scope

- Barcode scanning.
- Photo attachments.
- Supplier management.
- Purchase cost tracking.
- Depreciation.
- Marketplace listing.
- Cross-device inventory sync.

## User Stories

- As a rental owner, I want to add inventory by category so I can organize my catalog.
- As a rental owner, I want to track some items as individual units so I know exactly which physical item was rented.
- As a rental owner, I want to track other items by quantity so I can rent interchangeable stock without creating every unit manually.
- As a rental owner, I want to mark a unit as maintenance or lost so it cannot be rented by mistake.
- As a rental owner, I want to archive old inventory so it no longer appears in normal rental workflows.

## Functional Requirements

### FR1: Inventory List

The Inventory screen must show active inventory in a compact, scannable list.

Acceptance criteria:

- Serialized items and quantity stock are visually distinguishable.
- Rows show item name, category, pricing, availability state, and relevant count or unit detail.
- Archived inventory is hidden from the default list.

### FR2: Category Filters

The feature must support category filtering.

Initial filter examples:

- All.
- Camera.
- Audio.
- Tools.
- Outdoor.
- Power.
- Accessories.

Acceptance criteria:

- Filter chips update the visible inventory list.
- Category choices are flexible enough for hiking gear and other rental businesses.

### FR3: Search

The owner must be able to search inventory.

Acceptance criteria:

- Search matches item names.
- Search matches unit code or label for serialized units.
- Empty search results show a clear empty state.

### FR4: Add and Edit Item

The owner must be able to create and edit item records.

Required fields:

- Name.
- Category.
- Rental unit type: serialized or quantity.
- Default price.
- Notes.

Acceptance criteria:

- Name is required.
- Default price cannot be negative.
- Rental unit type is explicit and understandable.
- User-facing validation messages are concise and owner-friendly.

### FR5: Serialized Unit Management

For serialized items, the owner must be able to manage physical units.

Required fields:

- Unit code or label.
- Condition.
- Status.
- Notes.

Acceptance criteria:

- Unit code or label is visible in item detail and selection flows.
- Maintenance, lost, and archived units are unavailable for new rentals.
- Unit status changes are persisted locally.

### FR6: Quantity Stock Management

For quantity items, the owner must be able to manage total quantity.

Required fields:

- Total quantity.
- Optional low-stock threshold.
- Notes.

Acceptance criteria:

- Quantity cannot be negative.
- Available quantity is based on data engine availability rules.
- Low-stock and fully booked states can appear in list and dashboard consumers.

### FR7: Archive Behavior

The owner must be able to archive items and units.

Acceptance criteria:

- Archived items are hidden from normal inventory and rental selection flows.
- Archived units cannot be selected for new reserved or active rentals.
- Existing historical rental records remain readable.
- Archive actions use confirmation when data loss or workflow impact is possible.

## UX Requirements

- Use dense but readable list rows.
- Use status chips for availability states.
- Use warning colors only for operational attention: low stock, fully booked, maintenance, lost, and conflict states.
- Avoid marketplace browsing patterns, promotional cards, and stock imagery.
- Add `@Preview` for every composable view.

## Data Requirements

The feature consumes:

- Item records.
- Serialized unit records.
- Quantity stock records.
- Availability summaries.
- Category values.

The feature writes:

- Item create/update/archive changes.
- Unit create/update/status/archive changes.
- Quantity stock create/update changes.

## Dependencies

- Epic 1: App Foundation & Design System.
- Epic 2: Rental Data Engine.

## Success Metrics

- Owner can add inventory before creating rentals.
- Owner can distinguish serialized units from quantity stock at a glance.
- Unavailable inventory cannot be selected for new reserved or active rentals.
- Maintenance and lost units are visible without opening every item.

## Risks

- Forms can become too complex if serialized and quantity behaviors are mixed poorly.
- Category choices can become too specific to hiking gear if not flexible.
- Archive behavior can confuse owners if historical rentals disappear.

## Release Checklist

- Inventory list implemented.
- Item add/edit flow implemented.
- Serialized unit add/edit/status flow implemented.
- Quantity stock add/edit flow implemented.
- Search and filters implemented.
- Archive confirmation implemented.
- Empty states implemented.
- ViewModel tests use `core:testing` fakes.
- Compose previews added.
