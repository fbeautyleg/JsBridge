# Protocol

## [Web] communicate with the native platform

    Bridge.invoke(service_name, service_action, [params], callback)

callback

    function(err, data){}

## [Native] communicate with Web

    Bridge.invoke(service_name, service_action, [params], callback)

callback

    class Callback{
        
    }


# API

## Callback

1. onSuccess
2. onError
3. onCancel

## Promise

