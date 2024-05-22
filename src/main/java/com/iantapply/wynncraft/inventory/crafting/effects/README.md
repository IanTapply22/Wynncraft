## How are these stored as NBT on ingredients?
The effects are stored separately in keys that have UUIDs to differentiate them from each other. The actual NBT data for each
type of effect value is stored as the following:

**Note:** All NBT keys are stored as random UUIDs

### Duration Value
```json
{
  "typeId": 0,
  "value": 1
}
```

### Percentage Range Value
```json
{
  "typeId": 1,
  "effectId": 0,
  "min": {
    "value": 1
  },
  "max": {
    "value": 2
  }
}
```

### Fixed Value
```json
{
  "typeId": 2,
  "effectId": 0,
  "value": 1
}
```

### Range Value
```json
{
  "typeId": 3,
  "effectId": 0,
  "min": {
    "value": 1
  },
  "max": {
    "value": 2
  }
}
```

### Possible Value
```json
{
  "typeId": 4,
  "values": [
    {
      "typeId": 0,
      "effectId": 0,
      "value": 1
    },
    {
      "typeId": 1,
      "effectId": 1,
      "value": 2
    }
  ]
}
```