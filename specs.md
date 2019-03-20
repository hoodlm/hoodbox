The HOODBOX is a simple virtual machine for learning purposes.
Its specifications are described in this document, but they're likely to change
in the future. Backwards compatibility not guaranteed. Safety not guaranteed.

# High Level Specs
* 16 MB of memory (2^24)
* Eight 24-bit registers

# Instruction set
## 0: READ
`READ <MEMORY> <REGISTER>`

Read a byte from memory into a destination register address.

* `MEMORY`: a 24-bit memory address
* `REGISTER`: a 3-bit register address

## 1: WRITE
`WRITE <REGISTER> <MEMORY>`

Copy the lowest byte of data from a register into a destination memory address.

* `REGISTER`: a 3-bit register address
* `MEMORY`: a 24-bit memory address

## 2: SET
`SET <VALUE> <REGISTER>`

Set a constant 3-byte value into a register.

* `VALUE`: a 24-bit value
* `REGISTER`: a 3-bit register address

## 3: EQ
`EQ <REGISTER_1> <REGISTER_2> <DEST_REGISTER>`

Compare the contents of two registers for equality. If the equality check
passes, the contents of a destination register will be overwritten with
0xffffffff; if it does not pass, the destination register will be overwritten
with 0x00000000.

* `REGISTER_1`, `REGISTER_2`, `DEST_REGISTER`: 3-bit register addresses.

## 4: GT
`GT <REGISTER_1> <REGISTER_2> <DEST_REGISTER>`

Compare the contents of two registers. If the first is larger than the second,
the contents of a destination register will be overwritten with 0xffffffff;
if not, the destination register will be overwritten with 0x00000000.

* `REGISTER_1`, `REGISTER_2`, `DEST_REGISTER`: 3-bit register addresses.

## 5: UNUSED

## 6: UNUSED

## 7: UNUSED

## 8: UNUSED

## 9: UNUSED

## 10: READTWO
`READTWO <MEMORY> <REGISTER>`

Read two bytes from memory into a destination register address.

* `MEMORY`: a 24-bit memory address
* `REGISTER`: a 3-bit register address

## 11: READTHREE
`READTHREE <MEMORY> <REGISTER>`

Read three bytes from memory into a destination register address.

* `MEMORY`: a 24-bit memory address
* `REGISTER`: a 3-bit register address

## 12: WRITETWO
`WRITETWO <REGISTER> <MEMORY>`

Copy the lower two bytes of data from a register into a destination
memory address.

* `REGISTER`: a 3-bit register address
* `MEMORY`: a 24-bit memory address

## 13: WRITETHREE
`WRITETHREE <REGISTER> <MEMORY>`

Copy the three bytes of data from a register into a destination
memory address.

* `REGISTER`: a 3-bit register address
* `MEMORY`: a 24-bit memory address
