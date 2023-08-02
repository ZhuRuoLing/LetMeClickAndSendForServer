# Let Me Click and Send for Server

## TL;DR
Server-side version of [LetMeClickAndSend](https://modrinth.com/mod/letmeclickandsend) that reverses the run_command click event restriction introduced in 1.19.1-rc1.

## Why?

For example, we run the following command and then click on the displayed text:

```
/tellraw @a {"text":"click me to send \"hi\"","clickEvent":{"action":"run_command","value":"hi"}}
```

In vanilla 1.19.1+ you cannot say anything after clicking because hi is not a valid command (does not start with /).

With this mod, you will automatically send a hi message to the server after clicking, which is the same behavior as previous versions of Minecraft.

## How?

Unlike the original LetMeClickAndSend, which changed the client behavior, we introduce a random 5-character server-side command generated from [0-9A-za-z] to send a fake player chat.

If we detect a "run_command" action whose value doesn't start with "/", we replace it with the generated command. This way the client will always find a real command from the `run_command' action.

Still using the previous example, we would replace the value `"hi"` with something like `"/a2xBg hi"`, then client would correctly execute it to send `hi` from player.

As a result, the maximum length of the `run_command' action is reduced from 256 to 250, which is a client limitation.

## Contact

If you have any problems or suggestions, please let us know.

## Copyright

Concept by [Alex3236](https://github.com/alex3236), Authored by [ZhuRuoLing](https://github.com/ZhuRuoLing), [Alex3236](https://github.com/alex3236), [Optijava](https://github.com/OptiJava) and [HuajiMURsMC](https://github.com/HuajiMURsMC).
