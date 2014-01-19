Indexed HashSet
===============

This is like a cross between a HashMap and a HashSet. The approach when done well[^1] is a really memory-efficient way
of storing objects, indexed by one of their properties. Typically, most people do this using a HashMap, but that's
inefficient because...

  1. You store an extra reference to the key value for every object you hold
  2. The standard Java HashMap implementation is additionally memory-inefficient as it has an extra Entry instance for
  every object it stores.

So - Say you had Employee data like this...

    Employee: {
        id: "123",
        fullName: "Arthur Pewty",
        age: 50
    }

If you wanted a cache of these where you could retrieve any Employee based on its id, you'd probably use a HashMap...

    HashMap<String, Employee> EmployeeCache = new HashMap<>()

**BUT** - assuming a 64-bit VM with compressed oops switched on - this will involve the following memory overhead for
every single object you store in the map[2]...

  * An instance of Entry (16 bytes object header)
  * A reference to the Entry instance(8 bytes)
  * The entry's reference to the key (8 bytes)
  * The entry's hashcode, which it stores, presumably for performance reasons (8 bytes)

That's 40 bytes of waste per object! If you're using HashMaps for really big caches (or on memory constrained hardware)
that adds up. With an IndexedHashSet, you don't have any of that overhead.

Additionally, an IndexedHashSet can handle returning multiple records for a given value. So in the example above, you
could just as easily store them indexed by their age and it'll happily happily return lists of Employees for a given age.

[^1] The implementation I've written here was purely so I could play around with the approach of minimising memory use
    per object. I've been lazy in other areas (e.g. using a Set for hashslots) because optimising those areas wasn't
    what I was interested in. Maybe I'll revisit it some day.

[2] I haven't included the reference to the value as that's required, and the reference to the next node which is part
    of HashMap's hash-conflict implementation (although IMO there are more memory efficient ways of handling those
    too).
