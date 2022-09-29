# Crypto-Data-Collection
The purpose of this program is to collect information on cryptocurrencies for the possibility that there might exist an algorithm that if followed will
generate profit over time. This program is experimental, in practice there may not be (and probably isn't) an algorithm a program can follow to generate profit over time
given cryptocurrency data, but there is another reason I am making this program: to get practice using GUI, singleton, multithreading, saving data by serialization, 
and data structures.

For the first versions of this program, it collected stock market data, then for a time it juggled collecting data from the stock market and cryptocurrencies,
but through experimenting I realized cryptocurrencies are better for this experiment since
cryptocurrencies have no down time and there is no period where funds need to settle to trade. So I have switched it to only collect cryptocurrency data.

Over time I have learned new programming methods from my work, but my time is prioritized for school and my job, these projects sometimes sit on the back burner for
long periods of time, certain aspects of this program may not reflect my current methodology.

In its current state, this program tracks the top profiting crypto in the last 24 hours and records its data.

There are plans to add simultanious methods for collecting data from separate sources at the same time.

This program was soley designed to work on my spare computer. It clicks specific pixel positions
which probably will not match up with your monitor resolution and sizing settings. Possible plans to allow GUI settings for user to specify positions needed.
