# Epic 02 Rental Data Engine Implementation Plan

## Summary

Implement the offline-first data engine for RentalIn owner workflows: inventory, rentals, customers, availability, payments, returns, and dashboard summaries.

The engine supports the core loop from the product references: add inventory, create rentals with customer/date/items/payment/condition notes, prevent double-booking, start pickup, return items, track balances/refunds, and power dashboard attention lists. This epic does not include feature UI, CSV export implementation, login, sync, notifications, or online payments.

## Data Structure

Domain models live in `core:model` and stay Android-free. Room tables live in `core:database` and map to domain models through `core:data`.

### Domain Models

- `Item`: id, name, category, rental unit type, default price, notes, archived state.
- `ItemUnit`: id, parent item, code, condition, status, notes.
- `QuantityStock`: id, parent item, total quantity, optional low-stock threshold, notes.
- `Customer`: id, name, phone, optional identity number, optional address, notes.
- `RentalCustomerSnapshot`: optional saved customer link plus typed customer details.
- `Rental`: customer snapshot, dates, lifecycle status, lines, payment fields, pickup/return/general notes, and condition before/after rental.
- `RentalLine`: rental, item, optional serialized unit, quantity, price, and condition notes.
- `MoneyAmount`: stores minor units as `Long` to avoid floating-point money bugs.

### Room Tables

- `items`: item identity, name, category, rental unit type, default price, notes, archived state.
- `item_units`: serialized unit details and status, linked to `items`.
- `quantity_stocks`: one quantity stock row per quantity item, linked to `items`.
- `customers`: reusable customer records.
- `rentals`: customer snapshot, dates, status, aggregate payment fields, conditions, and notes.
- `rental_lines`: serialized or quantity rental lines linked to rentals and items.

Required indexes cover category/archive filtering, unit status, customer lookup, rental status/date/customer filtering, and rental-line lookup by rental, item, and unit.

## Implementation Phases

1. Add this plan file.
2. Add immutable domain models, ID value types, money/date helpers, and validation result types.
3. Replace the marker Room schema with real entities, DAOs, relations, and indexes.
4. Add repository contracts and Room-backed implementations with validation before persistence.
5. Add domain use cases for availability, lifecycle, payment, overdue/due-today, and dashboard summaries.
6. Add deterministic test fixtures, fakes, and unit tests for Epic 02 rules.

## Test Plan

Run:

```bash
./gradlew :core:model:test :core:domain:test :core:data:test
./gradlew :core:database:compileDebugKotlin :core:data:compileDebugKotlin
./gradlew :app:compileDebugKotlin
```

Required scenarios:

- Invalid date range is rejected.
- Negative price, deposit, payment, refund, and quantity are rejected.
- Serialized unit overlap blocks reserved/active rentals.
- Quantity availability subtracts overlapping reserved/active quantities.
- Returned/cancelled rentals do not block availability.
- Maintenance/lost/archived units cannot be selected.
- Reserved can become active.
- Active can become returned with return details.
- Allowed reserved/active rentals can be cancelled.
- Overdue and due-today derive from injected current date.
- Payment status covers unpaid, partial, paid, refund due, and refunded.
- Dashboard summaries include active, overdue, due-today, unpaid, low-stock, fully booked, maintenance, and lost records.

## Assumptions

- `RTK.md` is referenced by `AGENTS.md` but is not present in the workspace.
- Epic 02 creates the data engine only; feature screens and CSV export implementation remain for later epics.
- Default item price is treated as a suggested/manual rental-line price, not automatic per-day pricing.
- Payment tracking is operational and aggregate-based, not accounting-grade.
