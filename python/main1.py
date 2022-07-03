from flask import Flask, request, jsonify, Response
import requests


def create_app():
    app = Flask(__name__)
    app.config['JSON_AS_ASCII'] = False
    return app


app = create_app()
target_host = "43.142.108.162:7500"


# :8001/myhome

@app.before_request
def proxy():
    headers = {h[0]: h[1] for h in request.headers}
    url = f'{request.scheme}://{target_host}{request.full_path}'
    print(url)
    headers['x-token'] = '***'
    # 一些自己的逻辑...
    res = requests.request(request.method, url, data=request.json, headers=headers)
    return res


if __name__ == "__main__":
    app.run(debug=True)
