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

ItemStack Item = NBT.getItem();
```

Reading an NBT from an ItemStack
```java
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
System.out.println(NBT.toString());
```

| Class       | Description                        |
|:------------|:-----------------------------------|
| `NBTChunk`  | NBT Component of a **Chunk**       |

Writing an NBT for a Chunk
```java
NBTChunk NBT = new NBTChunk(/*Chunk*/);

NBTPersistentDataContainer Container = NBT.getPersistentDataContainer();
Container.setString("Data", "Hello, World!");

Container.setCompound(Container);
```

Reading a Chunk's NBT
```java
System.out.println(Container.toString());
```

| Class       | Description                        |
|:------------|:-----------------------------------|
| `NBTBlock`  | NBT Component of a **Block**       |

Writing an NBT for a Block
```java
NBTBlock NBT = new NBTBlock(/*Block*/);
NBT.setString("Data", "Hello, World!");

NBT.setCompound(NBT);
```

Reading a Block's NBT
```java
System.out.println(NBT.toString());
```

| Class            | Description                        |
|:-----------------|:-----------------------------------|
| `NBTTileEntity`  | NBT Component of a **TileEntity**  |

Writing an NBT for a TileEntity
```java
Block Block = (/* Sign Block */);

NBTTileEntity NBT = new NBTTileEntity(Block.getState());
NBT.setString("Text1", "Hello, World!");

NBT.setCompound(NBT);
```

Reading a TileEntity's NBT
```java
System.out.println(NBT.toString());
```

#### License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
