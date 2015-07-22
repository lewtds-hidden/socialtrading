# coding: utf-8

import flask
from flask import request, session, redirect, render_template, url_for
from models import UserDao
from flask.ext.login import \
    login_user, login_required, logout_user
from flask.ext import menu

from socialtrading import app
import tradeapi


menu.Menu(app=app)


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == "GET":
        return render_template("login.jinja.html")
    else:
        # FIXME Use Flask-WTF to validate the form format
        user_dao = UserDao()
        user = user_dao.get_user_by_username(request.form["username"])
        login_user(user)

        client = tradeapi.VndirectTradeApiClient()
        token = client.login(request.form["username"], request.form["password"])
        session["trade-api-token"] = token
        return token


# FIXME: GET should not have side effects
@app.route("/logout", methods=["GET", "POST"])
@login_required
def logout():
    logout_user()
    return redirect(url_for("index"))


@app.route("/")
@menu.register_menu(app, '.', u'Trang chủ', order=0)
def index():
    return flask.render_template("index.jinja.html")


@app.route("/account")
@menu.register_menu(app, '.account', u'Trang của tôi', order=1)
def account():
    return render_template("account.jinja.html")


@app.route("/traders")
@menu.register_menu(app, '.traders', u'Chiến lược gia', order=2)
def traders():
    return render_template("traders.jinja.html")


@menu.register_menu(app, '.help', u'Tìm hiểu về AutoTrade', order=3)
@app.route("/help")
def help():
    return render_template("help.jinja.html")


@app.route("/wizard")
def wizard():
    return render_template("wizard.jinja.html")
