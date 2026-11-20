# Manual smoke test

1. Run `mvn clean test`.
2. Start with `mvn spring-boot:run`.
3. Open `/register`, create a farmer account, then log in.
4. Open `/dashboard`; verify sample crops and markets.
5. `GET /api/public/crops` should return crop JSON.
6. `GET /api/public/crops/1/prices` should return today's seeded sample rows.
7. While authenticated, `POST /api/profit/compare` with a valid JSON body. Verify sorted net earnings.
8. Log in as the configured admin and verify `/admin`.
9. Try an `/api/admin/...` write endpoint as a farmer; it should be forbidden.
10. Test duplicate email and invalid registration input.

For secured Postman write calls, Spring Security CSRF is enabled. Use a session cookie and CSRF token or implement a dedicated stateless API security chain before automated API clients.
