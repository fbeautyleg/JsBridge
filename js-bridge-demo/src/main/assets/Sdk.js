class Sdk extends Api {
    constructor(bridge) {
        super(bridge);
    }

    getNativeUser(params) {
        return this.invoke("getNativeUser", params);
    }
}
