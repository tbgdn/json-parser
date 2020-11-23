grammar Json;
obj: '{' '}';
WS  :   [ \t\n\r]+ -> skip ; // toss out whitespace
