# BTELNYY-Server-Utils
 A minecraft Plugin.
This plugin is most useful for semi-vanilla minecraft survival servers who want to have as little plugins as possible
This plugin tries to fit in with the classic minecraft look with errors being red and info being yellow.

Features:
> `/suicide` kills the player who sent it along with a random message from `./plugins/btelnyy/death_msg.txt`
* Permission: `btelnyy.suicide`
* Used like /kill without needing to worry about players killing others
> `/rules` prints rules file from `./plugins/btelnyy/rules.txt`
* Permission: `btelnyy.rules`
* Used for printing server rules
> `/dc` kicks the player who ran the command
* Permission: `btelnyy.dc`
* Kicks the player who ran the command from the server
* Used for debugging by me, its kinda useless now.
> `/vtp <player> <kick/ban>` start a 30 second vote to kick or ban specific player
* Permission to start a vote: `btelnyy.vote.start`
* Kick punishment permission: `btelnyy.vote.kick`
* Ban punishment permission: `btelnyy.vote.ban`
* Usefull for community driven moderation, if you want the community to get rid of hackers without you, you can use this. REVIEW BAN REPORTS!
> `/voteyes` vote yes
* Permission: `btelnyy.vote`
> `/voteno` vote no
* Permission: `btelnyy.vote`
