# WhatsApp AI Android Architecture (Kotlin + Embedded NodeJS + Baileys + OpenAI)

## 1) High-level goals
- Android device is the only server runtime (no Termux, no VPS).
- Embedded NodeJS handles WhatsApp session via Baileys.
- Kotlin layer provides lifecycle, persistence, security, and UX.
- AI reply engine is relationship-first and style-imitation-first.

## 2) Component architecture
1. **Android App (Kotlin)**
   - Foreground service keeps system alive.
   - WorkManager handles retries, upload sync, model prompt refresh.
   - Local encrypted DB stores style signals and behavior metrics.
2. **Embedded Node Runtime**
   - Runs local HTTP server at `127.0.0.1:8787`.
   - Manages WhatsApp pairing code and message socket through Baileys.
3. **AI Orchestration Layer**
   - Silent learning mode first (collect samples, no auto replies).
   - Activation policy per contact with trust score threshold.
   - Style imitation prompt composer for natural responses.

## 3) Silent-learning pipeline
- Collect historical outgoing replies per contact.
- Extract style features: punctuation density, response latency, emoji ratio, avg sentence length.
- Build contact-level style profile and global profile fallback.
- Only auto-enable when minimum examples + confidence threshold are met.

## 4) Human typing clone safeguards
- Inject variable response delay windows (e.g., 20s–140s).
- Use context-aware short replies, follow-up questions, and occasional typos/self-correction rules.
- Maintain per-contact communication boundaries (night mode, sensitive topics blacklist).

## 5) Update-safe architecture
- Strict module boundaries (`data`, `domain`, `service`, `node bridge`).
- Node runtime versioned independently (`node-runtime/package.json`).
- Contract-first local API between Kotlin and Node (JSON schemas).
- Feature flags for rollout (`silent_learning`, `target_only`, `auto_reply_live`).

## 6) Security baseline
- Keep OpenAI API key encrypted at rest (Android Keystore).
- Localhost bind only for embedded APIs.
- Redact PII in logs and crash reports.
- Add remote kill-switch + emergency pause action in notification.

## 7) Production rollout phases
1. Phase A: Pairing + passive learning only.
2. Phase B: Suggestion-only mode (human approves every reply).
3. Phase C: Auto-reply for 1-2 trusted contacts.
4. Phase D: Controlled scale with analytics + guardrails.
