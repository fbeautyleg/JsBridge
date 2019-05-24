module.exports = {
    window: {
        BridgeInterface: {
            send(service, params, cbk) {
                service = JSON.parse(service);
                params = JSON.parse(params);
                setTimeout(() => {
                    if (service.action === 'getUser') {
                        let user = {
                            id: 12345678,
                            name: 'xesam',
                            age: 18
                        };
                        let err = null;
                        let data = user;
                        eval(`global.Bridge.receive(${cbk}, ${JSON.stringify(err)}, ${JSON.stringify(data)})`);
                    } else if (service.action === 'getLocation') {
                        let err = {
                            status: 403,
                            msg: 'permission denied'
                        };
                        eval(`global.Bridge.receive(${cbk}, ${JSON.stringify(err)})`);
                    } else {
                        let err = {
                            status: 404,
                            msg: 'service not found'
                        };
                        eval(`global.Bridge.receive(${cbk}, ${JSON.stringify(err)})`);
                    }
                }, 100);
            }
        }
    }
};
