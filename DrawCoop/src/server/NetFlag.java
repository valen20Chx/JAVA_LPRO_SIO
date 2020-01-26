enum NetFlag {
    BRUSH,
    POINT,
    COLOR,
    STRING,
    QUIT,
    NONE;

    private NetFlag() {}

    public int getValue() {
        return this.ordinal();
    }

    public static NetFlag getFlag(int value) {
    NetFlag flag = NetFlag.NONE;
        switch(value) {
            case 0:
                flag = NetFlag.BRUSH;
                break;
            case 1:
                flag = NetFlag.POINT;
                break;
            case 2:
                flag = NetFlag.COLOR;
                break;
            case 3:
                flag = NetFlag.STRING;
                break;
            default:
                flag = NetFlag.NONE;
                break;
        }
        return flag;
    }
}