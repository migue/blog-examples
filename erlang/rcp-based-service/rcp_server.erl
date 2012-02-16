%%%
%%% This is a small example for an Erlang/OTP behaviour.
%%%
%%% This module defines a server process in order to listen for
%%% incoming TCP connections and allows to execute commands.
%%%
%%% @author Miguel Pastor
%%%

-module(rcp_server).

-behaviour(gen_server)


%% library API
-export([
		start_link/1,
		start_link/2,
		get_count/0,
		stop/0
		]).

%% API implementation
start_link(Port) -> gen_server:start_link({local,?SERVER}, ?MODULE, Port, []).

start_link() -> start_link(?DEFAULT_PORT).

get_count() -> gen_server:call(?SERVER, get_count).

stop() -> gen_server:cast(?SERVER, stop).
			
%% Callbacks for the gen_server behaviour)
-export([
		init/1,
		handle_call/3,
		handle_cast/2,
		handle_info/2,
		terminate/2,
		code_change/3
		]).

%% Callbacks implementation
init([Port]) ->
		{ok, LSock} = gen_tcp:listen(Port, [{active, true}]),
		{ok, #state{port=Port, lsock=Lsock}, 0}.

handle_call(get_count, _From, State) ->
		{reply, {ok, State#state.request_count}, State}.

handle_cast(stop, State) ->
		{stop, normal, State}.

-define(SERVER, ?MODULE).
-define(DEFAULT_PORT, 1099).

-record(state, {port, lsock, request_count = 0}).