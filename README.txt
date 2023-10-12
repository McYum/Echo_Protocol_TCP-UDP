# Anti RK/RDM for FEGK
<img src="https://image.shutterstock.com/shutterstock/photos/2138257775/display_1500/stock-photo-a-very-sad-crying-emoticon-cartoon-face-icon-2138257775.jpg" width=10% height=10%>

By: BOBOH                         
made for FEGK by Thienbao2109

## Documentation
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#API">API</a>
      <ul>
        <li><a href="#Methods">Methods</a></li>
      </ul>
    </li>
    <li><a href="#Getting Started">Getting Started</a>
      <ul>
        <li><a href="##Setup/Installation">Setup/Installation</a></li>
      </ul>
    </li>
  </ol>
</details>

# API

## Methods

<details open>
<summary>List</summary>
<br>
	
```lua
RKTagger.SetPlayerTeam(player: player, Permanent: bool, kill: bool)
```
	
Refreshes the player team, Permanent adds the player to the server team lock, kill kills the player during the procedure.
	
---
```lua
RKTagger.RemoveImmunity(player: player)
```
	
Enables the player to get killed by other foundations members punishment free.
	
---
```lua
RKTagger.HumDied(Humanoid: obj)
```
Checks for the killer of the humanoid and punishes him accordingly.
	
---	
```lua
RKTagger.AddTagger(Humanoid: obj)
```	
Adds a No-Kill tag to the humanoid.
	
---
```lua
RKTagger.RemoveTagger(Humanoid)
```
	
Removes a No-Kill tag from the Humanoid.
	
Use case example punishment free D-Class killing zone
	
```lua
-- Require the module first
local Tagger = require(game.ServerScriptService.ServerModules.RKTagger)

-- Listen for objects touching the parent
script.Parent.Touched:Connect(function(obj)
    if obj.Parent.Humanoid:FindFirstChild("No-Kill") then	
	Tagger.RemoveTagger(obj.Parent.Humanoid) -- remove tag from player
    end
end)
```
	
</details>


# Getting Started

## Setup/Installation

### **Server setup (required)**
To get started with using the Anti-RK system require the module script each time you intend to use it as shown below.
You will also need a remoteEvent called NotifyPlayer inside game.ReplicatedStorage.Remotes.
```lua
-- This is a script you would create inside a server script
local RKTagger = require(Module_path)
```

To see if a player was killed by another one we will need to check each time a player dies as shown below.

```lua
-- this function will use the anti RK module to listen to killings, FEGK adds a killer Tag on default which we can use to determine the killer
local function setupkilltagger(obj)
    obj.Humanoid.Died:connect(function()
	-- The module will handle kill infractions
	RKTagger.HumDied(obj.Humanoid)
    end)
end

-- Listen for players dying
Players.PlayerAdded:Connect(function(player)
    wait()
    player.CharacterAdded:connect(function(Character)
    -- listen for death
    for i, v in pairs(_G.TeamLockedPlayers) do -- check if player is on the server team lock, list gets handled by the module
        if v.Name == player.Name then
	   RKTagger.SetPlayerTeam(v, false, false)
	end
     end
    	if player.Team == game.Teams.ClassD then -- Add a no kill tag for everyone in the ClassD team
	   local val = Instance.new("BoolValue")
	   val.Name = "No-Kill"
	   val.Parent = Character.Humanoid
	end
	setupkilltagger(Character)
    end)
    setupkilltagger(player.Character) -- Set it up once when the player joins for the first time
end)
```
Now incase you plan on using Anti RK on dummies you can do the following.

```lua
-- This will loop trough all existing dummies inside a folder in worpkspace called TaggedDummies and will set them up.
for i, v in pairs(game.Workspace.TaggedDummies:GetChildren()) do
   setupkilltagger(v)
end
-- This will setup any Dummie that might get added in the future, make sure its actually a character with a Humanoid.
game.Workspace.TaggedDummies.ChildAdded:Connect(function(v)
    setupkilltagger(v)
end)
```

This is how the finished script should look like.

```lua
-- Listen for players dying
for i, v in pairs(_G.TeamLockedPlayers) do -- check if player is on the server team lock, list gets handled by the module
    if v.Name == player.Name then
       RKTagger.SetPlayerTeam(v, false, false)
    end
end
Players.PlayerAdded:Connect(function(player)
    wait()
    player.CharacterAdded:connect(function(Character)
    -- listen for death
	setupkilltagger(Character)
    end)
    setupkilltagger(player.Character) -- Set it up once when the player joins for the first time
end)

-- This will loop trough all existing dummies inside a folder in worpkspace called TaggedDummies and will set them up.
for i, v in pairs(game.Workspace.TaggedDummies:GetChildren()) do
   setupkilltagger(v)
end
-- This will setup any dummy that might get added in the future, make sure its actually a character with a humanoid.
game.Workspace.TaggedDummies.ChildAdded:Connect(function(v)
    setupkilltagger(v)
end)
```

### **Client setup (optional)**
If you wish the player to be notified of any kill infractions then follow the instructions below.

```lua
-- Remotes
local Notif = game.ReplicatedStorage.Remotes.NotifyPlayer -- you only need to get the remote and listen for it.
local TS = game:GetService("TweenService")
local Debris = game:GetService("Debris")
local Player = game.Players.LocalPlayer

-- function that runs upon warned, run whatever you want here
local function warnplayer(reason, tk)
	-- in this case we create a notification
	-- reason is a string eg Team killing
	local Clone = script.Parent.Main.Template:Clone()
	Clone.Name = "Warn"
	Clone.Visible = true
	
	if tk == true then
		if Player.Character:GetAttribute("NoImmunity") then
			Clone.Info.Text = "You lost immunity foundation members can now kill you legally!"
		else
			Clone.Info.Text = "You have been caught killing ".. reason.. ", you can do that ".. Player.Character:GetAttribute("WarnedAmount2").. "x more and you will be punished!"
		end
	else
		if Player:GetAttribute("WarnedAmount") <= 0 then
			Clone.Info.Text = "You will be permanently set to D-Class on this server for mass RK."
		else
			Clone.Info.Text = "You have been caught killing ".. reason.. ", do that ".. Player:GetAttribute("WarnedAmount").. "x  more and you will be punished!"
		end
	end
	
	Clone.Parent = script.Parent.Main
	
	-- Basic UI animation
	Clone.Position = UDim2.new(-1, 0,0.354, 0)
	TS:Create(Clone, TweenInfo.new(1, Enum.EasingStyle.Quint, Enum.EasingDirection.InOut,0), {Position = UDim2.new(0, 0,0.354, 0)}):Play()
	task.delay(3, function()
		TS:Create(Clone, TweenInfo.new(1, Enum.EasingStyle.Quint, Enum.EasingDirection.InOut,0), {Position = UDim2.new(-1, 0,0.354, 0)}):Play()
	end)
	Debris:AddItem(Clone, 5)
end

-- Listen for warnings
Notif.OnClientEvent:Connect(function(reason, tk)
	warnplayer(reason, tk)
end)
```
