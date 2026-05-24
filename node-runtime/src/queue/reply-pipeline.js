import { decideIfAutoReplyEnabled } from '../style/activation-gate.js';
import { generateHumanLikeReply } from '../ai/reply-engine.js';

/**
 * Queue worker with silent-learning gate:
 * - phase 1: collect style signals, do not auto-reply
 * - phase 2: controlled auto-reply for whitelisted contacts
 */
export function createReplyPipeline({ wa }) {
  return {
    async enqueueIncoming(event) {
      const enabled = decideIfAutoReplyEnabled(event.contactJid);
      if (!enabled) return;

      const reply = await generateHumanLikeReply(event);
      await wa.sendText(event.contactJid, reply);
    },
  };
}
