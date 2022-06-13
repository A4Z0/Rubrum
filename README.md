# Rubrum [1.8 - 1.19]
Assistance in modifying NBTs in multiple versions.

<div>
  <a target="_blank" href="https://github.com/A4Z0/Rubrum/blob/master/LICENSE">
    <img alt="License" src="https://img.shields.io/github/license/A4Z0/Rubrum?style=for-the-badge">
  </a>
  <a target="_blank" href="https://jitpack.io/#A4Z0/Rubrum">
    <img alt="JitPack" src="https://img.shields.io/jitpack/v/github/A4Z0/Rubrum?style=for-the-badge">
  </a>
</div>

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

| Class         | Description                                               |
|:--------------|:----------------------------------------------------------|
| `NBTCompound` | Base of an NBT component. Stores data with class **Map**. |

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

NBT.setTag(NBT);

ItemStack Item = NBT.getItem();
```

Reading an NBT from an ItemStack
```java
System.out.println(NBT.toString());
```

| Class       | Description                        |
|:------------|:-----------------------------------|
| `NBTEntity` | NBT component of an **Entity**.    |

Writing an NBT for an Entity
```java
NBTEntity NBT = new NBTEntity(/*Entity*/);
NBT.setBoolean("Invisible", true);

NBT.setTag(NBT);
```

Reading an Entity's NBT
```java
System.out.println(NBT.toString());
```

| Class       | Description                        |
|:------------|:-----------------------------------|
| `NBTChunk`  | NBT component of a **Chunk**.      |

Writing an NBT for a Chunk
```java
NBTChunk NBT = new NBTChunk(/*Chunk*/);
NBT.setString("Data", "Hello, World!");

NBT.setTag(NBT);
```

Reading a Chunk's NBT
```java
System.out.println(NBT.toString());
```

| Class       | Description                        |
|:------------|:-----------------------------------|
| `NBTBlock`  | NBT Component of a **Block**.      |

Writing an NBT for a Block
```java
NBTBlock NBT = new NBTBlock(/*Block*/);
NBT.setString("Data", "Hello, World!");

NBT.setTag(NBT);
```

Reading a Block's NBT
```java
System.out.println(NBT.toString());
```

| Class            | Description                                   |
|:-----------------|:----------------------------------------------|
| `NBTTileEntity`  | NBT component of a **BlockState** TileEntity. |

Writing an NBT for a TileEntity
```java
Block Block = (/* Sign Block */);

NBTTileEntity NBT = new NBTTileEntity(Block.getState());
NBT.setString("Text1", "Hello, World!");

NBT.setTag(NBT);
```

Reading a TileEntity's NBT
```java
System.out.println(NBT.toString());
```

#### License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
