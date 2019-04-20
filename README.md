BBC-Sport-API
=============
(Please note this project is NOT designed for commercial use. Please see https://github.com/JPatrickDev/BBC-Sport-API/issues/1 for more information)

A java interface for the BBC Sport football scores.
The Main class contains a sample of fetching all of the current matches. The classes inside Models.match show what information is available.
The events API is not currently implemented, but is being worked on.


Important notes:
The boolean in the SportAPI constructor indicates if HTMLUnit should be used to run the BBC Sports page's JS before parsing. While this is not always necessary, and does add a significant(~2.5s)
delay to the return of the results, not doing so can often result in not all of the scores being returned, or sometimes scores from strange leagues when results from much more high profile games
are available if you view the scores in a browser. Seems the BBC don't actually return all of the scores in
the original request, but insert them client side with JS. There is a .async method that allows the request to be made easily on another thread and a FixtureListener to deal with the results, 
which should make the HTMLUnit mode much more usable. 

Please be considerate when using this library. Please try keep requests limited, once or twice a minute at most if possible.
