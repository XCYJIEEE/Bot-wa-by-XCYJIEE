import makeWASocket, { useMultiFileAuthState } from '@whiskeysockets/baileys';

export async function initWaSocket() {
  const { state, saveCreds } = await useMultiFileAuthState('auth');
  const socket = makeWASocket({ auth: state });
  socket.ev.on('creds.update', saveCreds);

  return {
    requestPairingCode: async (phoneNumber) => socket.requestPairingCode(phoneNumber),
    sendText: async (jid, text) => socket.sendMessage(jid, { text }),
  };
}
