export class SmartContractExecuteRequest {
  smartContractHashState: string;
  smartContractAddress: string;
  executionMethod: string;
  executionMethodParamsVals: string[];
  transactionInnerId: number;
  transactionSource: string;
  signatureBase58: string;
  tranFieldsBytesBase58: string;
  forgetNewState: boolean;
}
