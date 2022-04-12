# Rubrum (1.8-1.18)
Reflect classes from other versions.

### References
- [Dependencies](#dependencies)
- [Maven & Gradle](#maven--gradle)
- [Documentation](#documentation)
- [License](#license)

#### Dependencies
You need at least [Java 8](https://www.java.com/) running on your machine.

#### Maven & Gradle
> [Click here](https://jitpack.io/#A4Z0/Rubrum) and follow the instructions from [jitpack.io](https://jitpack.io/).


### Documentation

| Class     | Description                       |
|:----------|:----------------------------------|
| `NBTItem` | NBT component of an **ItemStack**. |

**Adding NBT to an ItemStack**
```java
NBTItem NBT = new NBTItem(new ItemStack(Material.IRON_SWORD));

NBT.setString("ID", "CUSTOM_ITEM_ID");

ItemStack Item = NBT.getItem();
```

**Reading NBT from an ItemStack**
```java
NBTItem NBT = new NBTItem(/*<A Item with NBT>*/);

System.out.println(NBT.serialize());
```

#### License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)