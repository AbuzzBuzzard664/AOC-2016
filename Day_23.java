import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Day_23 {
    static List<Instruction> program = new ArrayList<>();
    static int index;

    enum CMD {
        cpy((x, y) -> x),
        inc((x, y) -> x + 1),
        dec((x, y) -> x - 1),
        jnz(Day_23::jump),
        tgl(Day_23::toggle),
        mul((x, y) -> x * y);

        private final BiFunction<Integer, Integer, Integer> func;
        CMD opposite;

        static {
            inc.opposite = dec;
            cpy.opposite = jnz;
            jnz.opposite = cpy;
            dec.opposite = inc;
            tgl.opposite = inc;
        }

        private CMD(BiFunction<Integer, Integer, Integer> func) {
            this.func = func;
        }
    }

    static int toggle(int offset, int current) {
        if (current + offset < 0 || current + offset >= program.size()) return 0;
        Instruction temp = program.get(current + offset);
        temp.command = temp.command.opposite;
        return 0;
    }

    static int jump(int nonZero, int offset) {
        if (nonZero == 0) return index + 1;
        return index + offset;
    }

    static class Instruction {
        CMD command;
        Register fromRegister;
        Register toRegister;
        Register multRegister;
        int value;
        int offset;

        public Instruction(CMD command, Register fromRegister, Register toRegister,
                           Register multRegister, int value, int offset) {
            this.command = command;
            this.fromRegister = fromRegister;
            this.toRegister = toRegister;
            this.multRegister = multRegister;
            this.value = value;
            this.offset = offset;
        }

        public void execute() {
            switch (command) {
                case cpy -> {
                    if (toRegister != null) {
                        if (fromRegister == null) {
                            toRegister.value = value;
                        } else {
                            toRegister.value = fromRegister.value;
                        }
                    }
                    index++;
                }
                case inc, dec -> {
                    fromRegister.value = command.func.apply(fromRegister.value, 0);
                    index++;
                }
                case jnz -> {
                    if (toRegister != null) offset = toRegister.value;
                    if (fromRegister == null) {
                        index = command.func.apply(value, offset);
                    } else {
                        index = command.func.apply(fromRegister.value, offset);
                    }
                }
                case tgl -> {
                    command.func.apply(fromRegister.value, index);
                    index++;
                }
                case mul -> {
                    multRegister.value = command.func.apply(fromRegister.value, toRegister.value);
                    index += 6;
                }
            }
        }
    }

    public static void run() {
        while (index < program.size()) {
            Instruction instr = program.get(index);
            instr.execute();
        }
    }

    static class Register {
        int value = 0;
    }

    public static void main(String[] args) {
        List<String[]> input = GetPuzzleInput();
        Register[] register = new Register[4];
        for (int i = 0; i < 4; i++) {
            register[i] = new Register();
        }
        for (String[] line : input) {
            CMD command = CMD.valueOf(line[0]);
            char reg1 = line[1].charAt(0);
            Register from = (Character.isLetter(reg1)) ? register[reg1 - 'a'] : null;
            int value = (!Character.isLetter(reg1)) ? Integer.parseInt(line[1]) : 0;
            Register to = null;
            Register mult = null;
            int offset = 0;
            if (line.length == 3) {
                char reg2 = line[2].charAt(0);
                if (Character.isLetter(reg2)) to = register[reg2 - 'a'];
                if (!Character.isLetter(reg2)) offset = Integer.parseInt(line[2]);
            }
            if (line.length == 4) {
                char reg2 = line[2].charAt(0);
                char reg3 = line[3].charAt(0);
                to = register[reg2 - 'a'];
                mult = register[reg3 - 'a'];
            }
            program.add(new Instruction(command, from, to, mult, value, offset));
        }
        run();
        System.out.println(register[0].value);
    }

    public static List<String[]> GetPuzzleInput() {
        try {
            List<String> input = Files.readAllLines(Paths.get("Day_23.txt"));
            List<String[]> list = new ArrayList<>();
            for (String line : input) {
                list.add(line.split(" "));
            }
            return list;
        } catch (IOException e) {
            return null;
        }
    }
}