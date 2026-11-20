# AgriPrice architecture

Browser → Thymeleaf/Bootstrap → Spring MVC controllers → services → Spring Data JPA repositories → MySQL.

- `controller`: page and REST request handling.
- `service`: business rules, registration, and market comparison.
- `repository`: database access.
- `entity`: persistent domain objects and relationships.
- `dto`: validated request and response shapes.
- `config`: Spring Security and demo data initialization.
- `database`: portable schema reference.

## Recommendation algorithm
For each market with a price observation for the selected crop and date:
1. gross = quantity (kg) × price per kg
2. net = gross − transport expense − other expense
3. sort markets by net earnings descending
4. recommend the first row; if no rows exist, report no current price data.

The comparison intentionally does not select the highest per-kilogram price alone.
