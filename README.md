# BTELNYY-Server-Utils
 A minecraft Plugin.
This plugin is most useful for semi-vanilla minecraft survival servers who want to have as little plugins as possible
This plugin tries to fit in with the classic minecraft look with errors being red and info being yellow.
[Discord for support and updates](https://discord.gg/P22tFkjTm3)

## Config:
* Coming soon!

## Features:
**WARNING: Bellow data only applies to source code, actual jar data may be different.**
> `/rules` prints rules file from `./plugins/btelnyy/rules.txt`
* Permission: `btelnyy.command.rules`
* Used for printing server rules
> `/dc` kicks the player who ran the command
* Permission: `btelnyy.command.dc`
* Kicks the player who ran the command from the server
* Used for debugging by me, its kinda useless now.
> `/vtp <player> <kick/ban>` start a 30 second vote to kick or ban specific player
* Permission to start a vote: `btelnyy.vote.start`
* Kick punishment permission: `btelnyy.vote.kick`
* Ban punishment permission: `btelnyy.vote.ban`
* Usefull for community driven moderation, if you want the community to get rid of hackers without you, you can use this. REVIEW BAN REPORTS!
> `/vote` vote lol
* Usage: `/vote <yes/no>`
* Permission: `btelnyy.vote`
> `/voterestart` start a vote to restart the server
* Permission: `btelnyy.vote.srvrestart`
> `/ping` show your current ping to the server
* Permission: `btelnyy.command.ping`
> `/suicide` kills the player who sent it along with a random message from `./plugins/btelnyy/death_msg.txt`
* Used like /kill without needing to worry about players killing others
* Permission: `btelnyy.command.suicide`
