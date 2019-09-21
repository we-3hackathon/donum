from flask import Flask, escape, request, json, jsonify
import datetime
import jwt

app = Flask(__name__)

app.config['SECRET_KEY'] = 'YourContribution'


def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = "need to do"

        if not token:
            return "whatever"
        try:
            data = jwt.decode(token, app.config['SECRET_KEY'])
        except:
            return "need to do returns"

        return f(*args, **kwargs)
    
    return decorated

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
