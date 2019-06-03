const Host = require('./Host');
const Bridge = require('../Bridge');

const bridge = new Bridge(Host.window.BridgeInterface, global);

let cbk = (data) => {
    if (data.err) {
        console.log('发生错误', JSON.stringify(data.err));
    } else {
        console.log('没有错误', JSON.stringify(data.data));
    }
};

bridge.on((action, payload, callback) => {
    if (action.name === 'getWebHeight') {
        let height = 400 + payload.originHeight;
        setTimeout(function () {
            console.log(`getWebHeight=${height}`);
            callback(height);
        }, 1000);
    }
});

bridge.invoke('getUser', {id: 12345678}, cbk);
bridge.invoke('getLocation', null, cbk);
bridge.invoke('getXXX', null, cbk);
bridge.invoke('callback', null, null);
