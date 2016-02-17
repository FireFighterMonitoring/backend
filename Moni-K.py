#!/usr/bin/env python

import json
import urllib2
import time
import signal
import sys


host = 'http://localhost:8080/api/v1/data'
statusKey = 'status'
vitalSignsKey = 'vitalSigns'
heartRateKey = 'heartRate'
stepCountKey = 'stepCount'

heartRateStep = 20
stepCountStep = 1

data = {
    'ffId': '[Moni-K]',
    statusKey: 'OK',
    vitalSignsKey: {
        heartRateKey: 80,
        stepCountKey: 0
    }
}

def signal_handler(signal, frame):
    del data[vitalSignsKey]
    data[statusKey] = 'DISCONNECTED'
    post()
    sys.exit(0)

def post():
    data_json = json.dumps(data)
    print 'POST', data_json
    req = urllib2.Request(host, data_json, {'Content-Type': 'application/json'})
    urllib2.urlopen(req)

signal.signal(signal.SIGINT, signal_handler)
print 'Press Ctrl+C to disconnect'

while True:
    heartRate = data[vitalSignsKey][heartRateKey] + heartRateStep
    if heartRate > 200 or heartRate < 50:
        heartRateStep *= -1

    data[vitalSignsKey][heartRateKey] = heartRate
    data[vitalSignsKey][stepCountKey] += stepCountStep
    post()
    time.sleep(1)
