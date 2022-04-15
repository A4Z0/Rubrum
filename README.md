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

| Class         | Description    |
|:--------------|:---------------|
| `NBTCompound` | NBT component. |

Writing an NBT component
```java
NBTCompound NBT = new NBTCompound();
NBT.setString("Data", "Hello, World!");

return NBT;
```

Reading an NBT component
```java
System.out.println(NBT.toString());
```

| Class     | Description                        |
|:----------|:-----------------------------------|
| `NBTItem` | NBT component of an **ItemStack**. |

Writing an NBT to an ItemStack
```java
NBTItem NBT = new NBTItem(new ItemStack(Material.IRON_SWORD));
NBT.setString("Data", "Hello, World!");

NBT.setCompound(NBT);

return NBT.getItem();
```

Reading an NBT from an ItemStack
```java
NBTItem NBT = new NBTItem(/*ItemStack with NBT*/);

System.out.println(NBT.toString());
```

| Class       | Description                        |
|:------------|:-----------------------------------|
| `NBTEntity` | NBT Component of an **Entity**     |

Writing an NBT for an Entity
```java
NBTEntity NBT = new NBTEntity(/*Entity*/);
NBT.setBoolean("Invisible", true);

NBT.setCompound(NBT);
```

Reading an Entity's NBT
```java
NBTEntity NBT = new NBTEntity(/*Entity*/);

System.out.println(NBT.toString());
```

#### License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
