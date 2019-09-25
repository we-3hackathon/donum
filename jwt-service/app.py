from flask import Flask, escape, request, json, jsonify
import datetime
import jwt

app = Flask(__name__)

app.config['SECRET_KEY'] = 'YourContribution'


@app.route('/token-creation/user', methods=['GET', 'POST'])
def create_JWT():
    try:
        user = request.json
        token = jwt.encode({'user': user["email"], 'password': user["password"]}, app.config["SECRET_KEY"])
        token = jsonify({'token': token.decode('UTF-8')})
        return token, 201
    except Exception as e:
        return e, 400
        

if __name__ == "__main__":
    app.run(debug=True)
