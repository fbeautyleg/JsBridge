class Sdk extends Api {
    constructor(bridge) {
        super(bridge);
    }

    pay(params) {
        return this.invoke("_PAY", params);
    }
}
