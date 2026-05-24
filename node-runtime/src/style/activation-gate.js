const targetContacts = new Set();

export function setTargetContacts(jids) {
  targetContacts.clear();
  jids.forEach((jid) => targetContacts.add(jid));
}

export function decideIfAutoReplyEnabled(contactJid) {
  return targetContacts.has(contactJid);
}
