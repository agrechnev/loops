Check if a unidirectional linked list (chain of linked nodes) is looped on itself
Note that there can be a lead-in linear chain before the loop starts,
a shape like the number '9'

The idea is to compare each element to the last saved marker
We save markers at position 100, 200, 400, 800, 1600 etc
Once we run out of the lead-in and into the loop,
And once the distance between neighboring markers exceeds the loop length
We find the repetition


By Oleksiy Grechnyev

