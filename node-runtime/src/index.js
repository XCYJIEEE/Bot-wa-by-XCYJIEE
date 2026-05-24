import express from 'express';
import { initWaSocket } from './baileys/socket.js';
import { createReplyPipeline } from './queue/reply-pipeline.js';

/**
 * Embedded HTTP bridge consumed locally by Android app.
 * Bind to 127.0.0.1 only so it is never exposed externally.
 */
const app = express();
app.use(express.json());

const wa = await initWaSocket();
const pipeline = createReplyPipeline({ wa });

app.post('/pairing', async (req, res) => {
  const { phoneNumber } = req.body;
  const code = await wa.requestPairingCode(phoneNumber);
  res.json({ code });
});

app.post('/incoming', async (req, res) => {
  await pipeline.enqueueIncoming(req.body);
  res.json({ ok: true });
});

app.listen(8787, '127.0.0.1');
