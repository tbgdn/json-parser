grammar Json;
obj
  : '{' fieldDefinition (',' fieldDefinition)* '}'
  | '{' '}'
  ;
fieldDefinition
  : STRING ':' value
  ;
value: STRING                 # stringValue
      | NUMBER                # integerValue
      | obj                   # objectValue
      | 'true'                # trueValue
      | 'false'               # falseValue
      | 'null'                # nullValue
      ;
STRING: '"'[a-zA-Z0-9]+'"';
NUMBER: '0'|([1-9][0-9]*);
WS  :   [ \t\n\r]+ -> skip ; // toss out whitespace
