/**
 * Should call Android->OpenAI bridge or direct HTTPS depending on security model.
 */
export async function generateHumanLikeReply(event) {
  const incoming = event.text ?? '';
  return `Noted ❤️ ${incoming.slice(0, 40)}`;
}
