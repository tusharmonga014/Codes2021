public class client {
    public static void main(String[] args) {
        String feeder = "aaabbghdhhsqwassexdzcxftAQPSLWOIEUYRTFHNVMXQWERRTYUIOPLKJHGFDSAZXCVBNMLKAJSDKJDSHHKGADSMNBZCXMHGDISFUQYTRUIQTPPEIQTQRAFDGHDHBXVZCZDEXEDTEHEFMMVMVMCGFAFAATETTRYRIGIKGJjhbrkysecyfgbwugxvwtyqfuouircfhiowmpcqpqidjsQFHFLHPHOYITURUJMZNZBAFQEQTWUFHGITUENNXJHHDHDHDNCNJSKSKKKKSAKAKSKcgvhtybuhjnkmjluoootyryrteeeehcgccgfbsmmalllapapeoooyiyuvbxcxcxxwwqqqjdkkfuthgygnvmxnzbzgxtreurohplnkbjmvnvibugbturjdhsbkyegfwiaygdtpiojkylhihkgjduhrtfgeydgsrsvagxbxhvbcgcvsfxrxfstohphnoiukhmnjvlf";
        HuffmanED hed = new HuffmanED(feeder);
        String bc = hed.encodeString("Tushar");
        String ec = hed.decodeString(bc);
        System.out.println(bc + " : " + ec);
    }
}
