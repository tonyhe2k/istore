export class Payment {
    paymentMethod:string;
    ccNumber:string;
    expirationDate:string;
    securityCode:number;

    constructor(
        paymentMethod: string, 
        ccNumber: string, 
        expirationDate: string,
        securityCode: number) {
        this.paymentMethod = paymentMethod;
        this.ccNumber = ccNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }
}