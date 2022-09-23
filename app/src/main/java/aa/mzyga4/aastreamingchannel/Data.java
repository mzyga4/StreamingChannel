package aa.mzyga4.aastreamingchannel;

import aa.mzyga4.aastreamingchannel.model.Program;

public class Data {
    public static final Program p_NETFLIX = new Program(
            "Netflix", "s_netflix_", 19,
            "com.netflix.ninja",
            "");

    public static final Program p_PRIME = new Program(
            "Prime Video", "s_prime_", 11,
            "com.amazon.amazonvideo.livingroom",
            "com.amazon.ignition.IgnitionActivity");

    public static final Program p_DISNEY = new Program(
            "Disney+", "s_disney_", 11,
            "com.disney.disneyplus",
            "");

    public static final Program p_HBO = new Program(
            "HBO Max", "s_hbo_", 11,
            "com.hbo.hbonow",
            "com.hbo.max.HboMaxActivity");

    public static final Program p_POLSAT = new Program(
            "Polsat Box Go", "s_polsat_", 3,
            "pl.cyfrowypolsat.cpgo",
            "pl.cyfrowypolsat.cpframework.presentation.tv.launch.TvLaunchActivity");
}
