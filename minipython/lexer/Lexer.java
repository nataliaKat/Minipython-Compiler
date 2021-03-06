/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.lexer;

import java.io.*;
import java.util.*;
import minipython.node.*;

public class Lexer
{
    protected Token token;
    protected State state = State.INITIAL;

    private PushbackReader in;
    private int line;
    private int pos;
    private boolean cr;
    private boolean eof;
    private final StringBuffer text = new StringBuffer();

    protected void filter() throws LexerException, IOException
    {
    }

    public Lexer(PushbackReader in)
    {
        this.in = in;

        if(gotoTable == null)
        {
            try
            {
                DataInputStream s = new DataInputStream(
                    new BufferedInputStream(
                    Lexer.class.getResourceAsStream("lexer.dat")));

                // read gotoTable
                int length = s.readInt();
                gotoTable = new int[length][][][];
                for(int i = 0; i < gotoTable.length; i++)
                {
                    length = s.readInt();
                    gotoTable[i] = new int[length][][];
                    for(int j = 0; j < gotoTable[i].length; j++)
                    {
                        length = s.readInt();
                        gotoTable[i][j] = new int[length][3];
                        for(int k = 0; k < gotoTable[i][j].length; k++)
                        {
                            for(int l = 0; l < 3; l++)
                            {
                                gotoTable[i][j][k][l] = s.readInt();
                            }
                        }
                    }
                }

                // read accept
                length = s.readInt();
                accept = new int[length][];
                for(int i = 0; i < accept.length; i++)
                {
                    length = s.readInt();
                    accept[i] = new int[length];
                    for(int j = 0; j < accept[i].length; j++)
                    {
                        accept[i][j] = s.readInt();
                    }
                }

                s.close();
            }
            catch(Exception e)
            {
                throw new RuntimeException("The file \"lexer.dat\" is either missing or corrupted.");
            }
        }
    }

    public Token peek() throws LexerException, IOException
    {
        while(token == null)
        {
            token = getToken();
            filter();
        }

        return token;
    }

    public Token next() throws LexerException, IOException
    {
        while(token == null)
        {
            token = getToken();
            filter();
        }

        Token result = token;
        token = null;
        return result;
    }

    protected Token getToken() throws IOException, LexerException
    {
        int dfa_state = 0;

        int start_pos = pos;
        int start_line = line;

        int accept_state = -1;
        int accept_token = -1;
        int accept_length = -1;
        int accept_pos = -1;
        int accept_line = -1;

        int[][][] gotoTable = this.gotoTable[state.id()];
        int[] accept = this.accept[state.id()];
        text.setLength(0);

        while(true)
        {
            int c = getChar();

            if(c != -1)
            {
                switch(c)
                {
                case 10:
                    if(cr)
                    {
                        cr = false;
                    }
                    else
                    {
                        line++;
                        pos = 0;
                    }
                    break;
                case 13:
                    line++;
                    pos = 0;
                    cr = true;
                    break;
                default:
                    pos++;
                    cr = false;
                    break;
                };

                text.append((char) c);

                do
                {
                    int oldState = (dfa_state < -1) ? (-2 -dfa_state) : dfa_state;

                    dfa_state = -1;

                    int[][] tmp1 =  gotoTable[oldState];
                    int low = 0;
                    int high = tmp1.length - 1;

                    while(low <= high)
                    {
                        int middle = (low + high) / 2;
                        int[] tmp2 = tmp1[middle];

                        if(c < tmp2[0])
                        {
                            high = middle - 1;
                        }
                        else if(c > tmp2[1])
                        {
                            low = middle + 1;
                        }
                        else
                        {
                            dfa_state = tmp2[2];
                            break;
                        }
                    }
                }while(dfa_state < -1);
            }
            else
            {
                dfa_state = -1;
            }

            if(dfa_state >= 0)
            {
                if(accept[dfa_state] != -1)
                {
                    accept_state = dfa_state;
                    accept_token = accept[dfa_state];
                    accept_length = text.length();
                    accept_pos = pos;
                    accept_line = line;
                }
            }
            else
            {
                if(accept_state != -1)
                {
                    switch(accept_token)
                    {
                    case 0:
                        {
                            Token token = new0(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 1:
                        {
                            Token token = new1(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 2:
                        {
                            Token token = new2(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 3:
                        {
                            Token token = new3(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 4:
                        {
                            Token token = new4(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 5:
                        {
                            Token token = new5(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 6:
                        {
                            Token token = new6(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 7:
                        {
                            Token token = new7(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 8:
                        {
                            Token token = new8(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 9:
                        {
                            Token token = new9(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 10:
                        {
                            Token token = new10(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 11:
                        {
                            Token token = new11(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 12:
                        {
                            Token token = new12(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 13:
                        {
                            Token token = new13(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 14:
                        {
                            Token token = new14(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 15:
                        {
                            Token token = new15(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 16:
                        {
                            Token token = new16(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 17:
                        {
                            Token token = new17(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 18:
                        {
                            Token token = new18(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 19:
                        {
                            Token token = new19(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 20:
                        {
                            Token token = new20(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 21:
                        {
                            Token token = new21(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 22:
                        {
                            Token token = new22(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 23:
                        {
                            Token token = new23(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 24:
                        {
                            Token token = new24(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 25:
                        {
                            Token token = new25(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 26:
                        {
                            Token token = new26(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 27:
                        {
                            Token token = new27(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 28:
                        {
                            Token token = new28(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 29:
                        {
                            Token token = new29(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 30:
                        {
                            Token token = new30(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 31:
                        {
                            Token token = new31(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 32:
                        {
                            Token token = new32(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 33:
                        {
                            Token token = new33(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 34:
                        {
                            Token token = new34(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 35:
                        {
                            Token token = new35(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 36:
                        {
                            Token token = new36(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 37:
                        {
                            Token token = new37(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 38:
                        {
                            Token token = new38(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 39:
                        {
                            Token token = new39(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 40:
                        {
                            Token token = new40(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 41:
                        {
                            Token token = new41(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 42:
                        {
                            Token token = new42(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 43:
                        {
                            Token token = new43(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 44:
                        {
                            Token token = new44(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 45:
                        {
                            Token token = new45(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 46:
                        {
                            Token token = new46(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 47:
                        {
                            Token token = new47(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 48:
                        {
                            Token token = new48(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 49:
                        {
                            Token token = new49(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    case 50:
                        {
                            Token token = new50(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            pos = accept_pos;
                            line = accept_line;
                            return token;
                        }
                    }
                }
                else
                {
                    if(text.length() > 0)
                    {
                        throw new LexerException(
                            "[" + (start_line + 1) + "," + (start_pos + 1) + "]" +
                            " Unknown token: " + text);
                    }
                    else
                    {
                        EOF token = new EOF(
                            start_line + 1,
                            start_pos + 1);
                        return token;
                    }
                }
            }
        }
    }

    Token new0(String text, int line, int pos) { return new TTab(text, line, pos); }
    Token new1(int line, int pos) { return new TPlus(line, pos); }
    Token new2(int line, int pos) { return new TMinusEq(line, pos); }
    Token new3(int line, int pos) { return new TMinus(line, pos); }
    Token new4(int line, int pos) { return new TDmult(line, pos); }
    Token new5(int line, int pos) { return new TMult(line, pos); }
    Token new6(int line, int pos) { return new TDivEq(line, pos); }
    Token new7(int line, int pos) { return new TDiv(line, pos); }
    Token new8(int line, int pos) { return new TMod(line, pos); }
    Token new9(int line, int pos) { return new TCompEq(line, pos); }
    Token new10(int line, int pos) { return new TGreatEq(line, pos); }
    Token new11(int line, int pos) { return new TLessEq(line, pos); }
    Token new12(int line, int pos) { return new TNotEq(line, pos); }
    Token new13(int line, int pos) { return new TEq(line, pos); }
    Token new14(int line, int pos) { return new TExclam(line, pos); }
    Token new15(int line, int pos) { return new TDef(line, pos); }
    Token new16(int line, int pos) { return new TLPar(line, pos); }
    Token new17(int line, int pos) { return new TRPar(line, pos); }
    Token new18(int line, int pos) { return new TLBr(line, pos); }
    Token new19(int line, int pos) { return new TRBr(line, pos); }
    Token new20(int line, int pos) { return new TComma(line, pos); }
    Token new21(int line, int pos) { return new TAnd(line, pos); }
    Token new22(int line, int pos) { return new TOr(line, pos); }
    Token new23(int line, int pos) { return new TNot(line, pos); }
    Token new24(int line, int pos) { return new TIf(line, pos); }
    Token new25(int line, int pos) { return new TElif(line, pos); }
    Token new26(int line, int pos) { return new TElse(line, pos); }
    Token new27(int line, int pos) { return new TWhile(line, pos); }
    Token new28(int line, int pos) { return new TFor(line, pos); }
    Token new29(int line, int pos) { return new TIn(line, pos); }
    Token new30(int line, int pos) { return new TPrint(line, pos); }
    Token new31(int line, int pos) { return new TReturn(line, pos); }
    Token new32(int line, int pos) { return new TAssert(line, pos); }
    Token new33(int line, int pos) { return new TLen(line, pos); }
    Token new34(int line, int pos) { return new TMax(line, pos); }
    Token new35(int line, int pos) { return new TMin(line, pos); }
    Token new36(int line, int pos) { return new TImport(line, pos); }
    Token new37(int line, int pos) { return new TFrom(line, pos); }
    Token new38(int line, int pos) { return new TNone(line, pos); }
    Token new39(int line, int pos) { return new TAs(line, pos); }
    Token new40(int line, int pos) { return new TLess(line, pos); }
    Token new41(int line, int pos) { return new TGreat(line, pos); }
    Token new42(int line, int pos) { return new TTrue(line, pos); }
    Token new43(int line, int pos) { return new TSemi(line, pos); }
    Token new44(int line, int pos) { return new TFalse(line, pos); }
    Token new45(String text, int line, int pos) { return new TBlank(text, line, pos); }
    Token new46(String text, int line, int pos) { return new TLineComment(text, line, pos); }
    Token new47(String text, int line, int pos) { return new TNumber(text, line, pos); }
    Token new48(String text, int line, int pos) { return new TId(text, line, pos); }
    Token new49(String text, int line, int pos) { return new TStringLiteral(text, line, pos); }
    Token new50(int line, int pos) { return new TSep(line, pos); }

    private int getChar() throws IOException
    {
        if(eof)
        {
            return -1;
        }

        int result = in.read();

        if(result == -1)
        {
            eof = true;
        }

        return result;
    }

    private void pushBack(int acceptLength) throws IOException
    {
        int length = text.length();
        for(int i = length - 1; i >= acceptLength; i--)
        {
            eof = false;

            in.unread(text.charAt(i));
        }
    }

    protected void unread(Token token) throws IOException
    {
        String text = token.getText();
        int length = text.length();

        for(int i = length - 1; i >= 0; i--)
        {
            eof = false;

            in.unread(text.charAt(i));
        }

        pos = token.getPos() - 1;
        line = token.getLine() - 1;
    }

    private String getText(int acceptLength)
    {
        StringBuffer s = new StringBuffer(acceptLength);
        for(int i = 0; i < acceptLength; i++)
        {
            s.append(text.charAt(i));
        }

        return s.toString();
    }

    private static int[][][][] gotoTable;
/*  {
        { // INITIAL
            {{9, 9, 1}, {10, 10, 2}, {13, 13, 3}, {32, 32, 4}, {33, 33, 5}, {34, 34, 6}, {35, 35, 7}, {37, 37, 8}, {39, 39, 9}, {40, 40, 10}, {41, 41, 11}, {42, 42, 12}, {43, 43, 13}, {44, 44, 14}, {45, 45, 15}, {46, 46, 16}, {47, 47, 17}, {48, 57, 18}, {58, 58, 19}, {60, 60, 20}, {61, 61, 21}, {62, 62, 22}, {65, 77, 23}, {78, 78, 24}, {79, 90, 23}, {91, 91, 25}, {93, 93, 26}, {95, 95, 27}, {97, 97, 28}, {98, 99, 29}, {100, 100, 30}, {101, 101, 31}, {102, 102, 32}, {103, 104, 29}, {105, 105, 33}, {106, 107, 29}, {108, 108, 34}, {109, 109, 35}, {110, 110, 36}, {111, 111, 37}, {112, 112, 38}, {113, 113, 29}, {114, 114, 39}, {115, 115, 29}, {116, 116, 40}, {117, 118, 29}, {119, 119, 41}, {120, 122, 29}, },
            {},
            {},
            {},
            {},
            {{61, 61, 42}, },
            {{0, 9, 43}, {11, 12, 43}, {14, 33, 43}, {34, 34, 44}, {35, 127, 43}, },
            {{0, 9, 45}, {10, 10, 46}, {11, 12, 45}, {13, 13, 47}, {14, 127, 45}, },
            {},
            {{0, 9, 48}, {11, 12, 48}, {14, 38, 48}, {39, 39, 49}, {40, 127, 48}, },
            {},
            {},
            {{42, 42, 50}, },
            {},
            {},
            {{61, 61, 51}, },
            {},
            {{61, 61, 52}, },
            {{46, 46, 53}, {48, 57, 18}, },
            {},
            {{61, 61, 54}, },
            {{61, 61, 55}, },
            {{61, 61, 56}, },
            {{48, 57, 57}, {65, 90, 58}, {95, 95, 59}, {97, 122, 60}, },
            {{48, 95, -25}, {97, 110, 60}, {111, 111, 61}, {112, 122, 60}, },
            {},
            {},
            {{48, 122, -25}, },
            {{48, 95, -25}, {97, 109, 60}, {110, 110, 62}, {111, 114, 60}, {115, 115, 63}, {116, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 95, -25}, {97, 100, 60}, {101, 101, 64}, {102, 122, 60}, },
            {{48, 95, -25}, {97, 107, 60}, {108, 108, 65}, {109, 122, 60}, },
            {{48, 95, -25}, {97, 97, 66}, {98, 110, 60}, {111, 111, 67}, {112, 113, 60}, {114, 114, 68}, {115, 122, 60}, },
            {{48, 95, -25}, {97, 101, 60}, {102, 102, 69}, {103, 108, 60}, {109, 109, 70}, {110, 110, 71}, {111, 122, 60}, },
            {{48, 100, -32}, {101, 101, 72}, {102, 122, 60}, },
            {{48, 95, -25}, {97, 97, 73}, {98, 104, 60}, {105, 105, 74}, {106, 122, 60}, },
            {{48, 110, -26}, {111, 111, 75}, {112, 122, 60}, },
            {{48, 95, -25}, {97, 113, 60}, {114, 114, 76}, {115, 122, 60}, },
            {{48, 113, -39}, {114, 114, 77}, {115, 122, 60}, },
            {{48, 100, -32}, {101, 101, 78}, {102, 122, 60}, },
            {{48, 113, -39}, {114, 114, 79}, {115, 122, 60}, },
            {{48, 95, -25}, {97, 103, 60}, {104, 104, 80}, {105, 122, 60}, },
            {},
            {{0, 127, -8}, },
            {},
            {{0, 127, -9}, },
            {},
            {{10, 10, 81}, },
            {{0, 127, -11}, },
            {},
            {},
            {},
            {},
            {{48, 57, 82}, },
            {},
            {},
            {},
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 109, -30}, {110, 110, 83}, {111, 122, 60}, },
            {{48, 95, -25}, {97, 99, 60}, {100, 100, 84}, {101, 122, 60}, },
            {{48, 95, -25}, {97, 114, 60}, {115, 115, 85}, {116, 122, 60}, },
            {{48, 101, -35}, {102, 102, 86}, {103, 122, 60}, },
            {{48, 95, -25}, {97, 104, 60}, {105, 105, 87}, {106, 114, 60}, {115, 115, 88}, {116, 122, 60}, },
            {{48, 107, -33}, {108, 108, 89}, {109, 122, 60}, },
            {{48, 113, -39}, {114, 114, 90}, {115, 122, 60}, },
            {{48, 110, -26}, {111, 111, 91}, {112, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 95, -25}, {97, 111, 60}, {112, 112, 92}, {113, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 109, -30}, {110, 110, 93}, {111, 122, 60}, },
            {{48, 95, -25}, {97, 119, 60}, {120, 120, 94}, {121, 122, 60}, },
            {{48, 109, -30}, {110, 110, 95}, {111, 122, 60}, },
            {{48, 95, -25}, {97, 115, 60}, {116, 116, 96}, {117, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 104, -67}, {105, 105, 97}, {106, 122, 60}, },
            {{48, 115, -77}, {116, 116, 98}, {117, 122, 60}, },
            {{48, 95, -25}, {97, 116, 60}, {117, 117, 99}, {118, 122, 60}, },
            {{48, 104, -67}, {105, 105, 100}, {106, 122, 60}, },
            {},
            {{48, 57, 82}, },
            {{48, 100, -32}, {101, 101, 101}, {102, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 100, -32}, {101, 101, 102}, {102, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 101, -35}, {102, 102, 103}, {103, 122, 60}, },
            {{48, 100, -32}, {101, 101, 104}, {102, 122, 60}, },
            {{48, 114, -65}, {115, 115, 105}, {116, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 95, -25}, {97, 108, 60}, {109, 109, 106}, {110, 122, 60}, },
            {{48, 110, -26}, {111, 111, 107}, {112, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 109, -30}, {110, 110, 108}, {111, 122, 60}, },
            {{48, 116, -81}, {117, 117, 109}, {118, 122, 60}, },
            {{48, 100, -32}, {101, 101, 110}, {102, 122, 60}, },
            {{48, 107, -33}, {108, 108, 111}, {109, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 113, -39}, {114, 114, 112}, {115, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 100, -32}, {101, 101, 113}, {102, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 113, -39}, {114, 114, 114}, {115, 122, 60}, },
            {{48, 115, -77}, {116, 116, 115}, {117, 122, 60}, },
            {{48, 113, -39}, {114, 114, 116}, {115, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 100, -32}, {101, 101, 117}, {102, 122, 60}, },
            {{48, 115, -77}, {116, 116, 118}, {117, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 115, -77}, {116, 116, 119}, {117, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 109, -30}, {110, 110, 120}, {111, 122, 60}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
            {{48, 122, -25}, },
        }
    };*/

    private static int[][] accept;
/*  {
        // INITIAL
        {-1, 0, 45, 45, 45, 14, -1, -1, 8, -1, 16, 17, 5, 1, 20, 3, 50, 7, 47, 43, 40, 13, 41, 48, 48, 18, 19, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 12, -1, 49, -1, 46, 46, -1, 49, 4, 2, 6, -1, 11, 9, 10, 48, 48, 48, 48, 48, 48, 39, 48, 48, 48, 48, 48, 24, 48, 29, 48, 48, 48, 48, 22, 48, 48, 48, 48, 46, 47, 48, 21, 48, 15, 48, 48, 48, 28, 48, 48, 33, 34, 35, 23, 48, 48, 48, 48, 38, 48, 25, 26, 48, 37, 48, 48, 48, 42, 48, 48, 44, 48, 30, 48, 27, 32, 36, 31, },

    };*/

    public static class State
    {
        public final static State INITIAL = new State(0);

        private int id;

        private State(int id)
        {
            this.id = id;
        }

        public int id()
        {
            return id;
        }
    }
}
