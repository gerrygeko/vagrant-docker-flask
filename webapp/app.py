import asyncio
import uvloop
from quart import Quart, jsonify, request

app = Quart(__name__)
teams = {}


@app.route("/")
async def hello():
    return "go to /teams/<name_of_the_team> to get details"

@app.route("/allteams")
async def get_all_teams():
    return jsonify(teams)

@app.route("/teams/<string:teamname>")
async def get_team(teamname):
    return jsonify(teams[teamname])

@app.route("/floor/<int:floor>")
async def get_teams_per_floor(floor):
    teams_found = {}
    for name, value in teams.items():
        if value['floor'] is floor:
            teams_found[name] = {
                "name": value['name'],
                "department": value['department']
            }
    return jsonify(teams_found)

@app.route("/addteam", methods=["POST"])
async def add_team():
    data = await request.get_json()
    if data is None:
        return "Bad or missing JSON body", 400

    teams[data['name']] = {
        "name": data['name'],
        "department": data['department'],
        "floor": data['floor']
    }

    return "ok", 200

if __name__ == "__main__":
    team_zythos = {
        "name": "zythos",
        "department": "development",
        "floor": 4
    }
    teams["zythos"] = team_zythos
    loop = uvloop.new_event_loop()
    asyncio.set_event_loop(loop)
    app.run(host='0.0.0.0', port=80, loop=loop)