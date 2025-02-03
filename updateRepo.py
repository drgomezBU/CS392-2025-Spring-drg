import subprocess

def run_command(command):
    result = subprocess.run(command, shell=True, capture_output=True, text=True)
    if result.returncode != 0:
        print(f"Error executing command: {command}")
        print(result.stderr)
    else:
        print(result.stdout)

command = (
    "git fetch upstream && "
    "git merge upstream/main main"
)

run_command(command)