# btelnyy-core
 A minecraft Plugin.
This plugin is most useful for semi-vanilla minecraft survival servers who want to have as little plugins as possible
This plugin tries to fit in with the classic minecraft look with errors being red and info being gray.
Yes, I know minecraft has white info text and no "Error:" before errors. I wanted this plugin to look similar, but still be different enough.
[Discord for support and updates](https://discord.gg/P22tFkjTm3)

This takes a very long time to make, I don't ask for much, please just join the discord.

## Config:
> `vote_timer` (Int)
* Set the timer for vote, accepts decimals
* Default: 30
> `default_pvp_toggle` (boolean)
* True for pvp on, false for pvp off
* Default: true
> `on_hardcore_death` (String)
* What should the plugin do when players die while `/hardcore` or `hardcore-mode` is true?
* Options:
  - `SPECTATOR` Set the player to spectator
  - `GHOST` Meant to be similar to what hypixel does in thier minigames
> `tp_to_death_hardcore` (boolean)
* Should players be teleported to where they died while `/hardcore` or `hardcore-mode` is true?
* Default: true
> `show_death_title` (boolean)
* Should players see the death title and subtitle? (not screen) (text editing comming soon!)
* Default: true
## Features:
**WARNING: Bellow data only applies to source code, actual jar data may be different.**
> `/pvp` enable or disable global pvp
* Permission: `btelnyy.command.pvp`
> `/hardcore` toggle the servers hardcore mode, this means that players have one life
* Permission: `btelnyy.command.hardcore`
> `/revive` Revive a player who died while `/hardcore` was on
* Permission `btelnyy.command.revive`
> `/reviveall` Revive all dead players
* Permission: `btelnyy.command.reviveall`
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
