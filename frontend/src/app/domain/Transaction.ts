export class Transaction {
  innerId: number;
  source: string;
  target: string;
  amount: number;
  balance :  number;
  currency: number;
  signatureBase58: string;
  offeredMaxFee: number;
  tranFieldsBytesBase58: string;
}
