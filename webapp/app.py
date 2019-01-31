from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
  return "Hello World!"

@app.route("/<string:instanceid>")
def show_name(instanceid):
    return "Your name is {}".format(instanceid)

if __name__ == "__main__":
  app.run(host='0.0.0.0', port=80)