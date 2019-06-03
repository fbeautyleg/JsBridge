module.exports = {
    window: {
        BridgeInterface: {
            invoke(action, payload, callback) {
                action = JSON.parse(action);
                payload = JSON.parse(payload);
                callback = JSON.parse(callback);
                let cbkAction = callback ? {
                    type: 1,
                    name: callback && callback.name
                } : {type: 1};
                setTimeout(() => {
                    if (action.type === 1) {
                        console.log('host callback', JSON.stringify(action), JSON.stringify(payload));
                    } else {
                        if (action.name === 'getUser') {
                            let user = {
                                id: 12345678,
                                name: 'xesam',
                                age: 18
                            };
                            let err = null;
                            let data = {
                                err: err,
                                data: user
                            };
                            eval(`global._WEB_BRIDGE(${JSON.stringify(cbkAction)}, ${JSON.stringify(data)})`);
                        } else if (action.name === 'getLocation') {
                            let err = {
                                status: 403,
                                msg: 'permission denied'
                            };
                            let data = {
                                err: err
                            };
                            eval(`global._WEB_BRIDGE(${JSON.stringify(cbkAction)}, ${JSON.stringify(data)})`);
                        } else if (action.name === "callback") {
                            let webAction = {
                                type: 0,
                                name: "getWebHeight"
                            };
                            let payload = {
                                originHeight: 100
                            };
                            eval(`global._WEB_BRIDGE(${JSON.stringify(webAction)}, ${JSON.stringify(payload)})`);
                        } else {
                            let err = {
                                status: 404,
                                msg: 'action not found'
                            };
                            let data = {
                                err: err
                            };
                            eval(`global._WEB_BRIDGE(${JSON.stringify(cbkAction)}, ${JSON.stringify(data)})`);
                        }
                    }
                }, 100);
            }
        }
    }
};
