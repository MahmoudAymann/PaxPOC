package com.mayman.myapplication.vfprinter;

public class VfPrinterParamIn {
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 2;
    public static final int FONT_LARGE = 3;
    public static final int FONT_NORMAL = 1;
    public static final int FONT_NORMAL_BIGGER = 2;
    public static final int FONT_SMALL = 0;
    public static final int PRINT_BARCODE = 2;
    public static final int PRINT_IMAGE = 3;
    public static final int PRINT_PAPERFEED = 4;
    public static final int PRINT_QRCODE = 1;
    public static final int PRINT_TEXT = 0;
    public static final int PRINT_TEXT_IN_LINE = 5;
    private int align;
    private String barCode;
    private String content;
    private int font;
    private String fontStyle;
    private int gray;
    private int height;
    private byte[] imageData;
    private boolean isBold;
    private String leftContent;
    private int lineSpace;
    private int lines;
    private String middleContent;
    private int mode;
    private boolean newline;
    private int offset;
    private int pixelPoint;
    private String qrCode;
    private String rightContent;
    private int type;
    private int width;

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode2) {
        this.mode = mode2;
    }

    public int getLineSpace() {
        return this.lineSpace;
    }

    public void setLineSpace(int lineSpace2) {
        this.lineSpace = lineSpace2;
    }

    public String getFontStyle() {
        return this.fontStyle;
    }

    public void setFontStyle(String fontStyle2) {
        this.fontStyle = fontStyle2;
    }

    public int getFont() {
        return this.font;
    }

    public void setFont(int font2) {
        this.font = font2;
    }

    public int getAlign() {
        return this.align;
    }

    public void setAlign(int align2) {
        this.align = align2;
    }

    public int getGray() {
        return this.gray;
    }

    public void setGray(int gray2) {
        this.gray = gray2;
    }

    public boolean isNewline() {
        return this.newline;
    }

    public void setNewline(boolean newline2) {
        this.newline = newline2;
    }

    public boolean isBold() {
        return this.isBold;
    }

    public void setBold(boolean bold) {
        this.isBold = bold;
    }

    public int getPixelPoint() {
        return this.pixelPoint;
    }

    public void setPixelPoint(int pixelPoint2) {
        this.pixelPoint = pixelPoint2;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset2) {
        this.offset = offset2;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width2) {
        this.width = width2;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height2) {
        this.height = height2;
    }

    public int getLines() {
        return this.lines;
    }

    public void setLines(int lines2) {
        this.lines = lines2;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content2) {
        this.content = content2;
    }

    public String getLeftContent() {
        return this.leftContent;
    }

    public void setLeftContent(String leftContent2) {
        this.leftContent = leftContent2;
    }

    public String getMiddleContent() {
        return this.middleContent;
    }

    public void setMiddleContent(String middleContent2) {
        this.middleContent = middleContent2;
    }

    public String getRightContent() {
        return this.rightContent;
    }

    public void setRightContent(String rightContent2) {
        this.rightContent = rightContent2;
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void setBarCode(String barCode2) {
        this.barCode = barCode2;
    }

    public String getQrCode() {
        return this.qrCode;
    }

    public void setQrCode(String qrCode2) {
        this.qrCode = qrCode2;
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public void setImageData(byte[] imageData2) {
        this.imageData = imageData2;
    }
}
